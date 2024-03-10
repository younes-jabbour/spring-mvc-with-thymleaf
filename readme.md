# Activité Pratique N°3 : Spring MVC Spring Data JPA Thymeleaf

![Spring MVC Spring Data JPA Thymeleaf](/img/result.png)

## - Partie 1 : La gestion des Patients

- #### Affichage et la recherche des patients.

apres une requete `GET` sur l'URL `http://localhost:8080/user/index` on doit afficher la liste des patients.

Premierement, le controlleur `PatientController` doit retourner la liste des patients en utilisant la
methode `searchPatient`
de l'interface `IHospitalService`, et par la suite il passe le modele a la vue `Patient` cree en utilisant le Thymeleaf.

Le résultat de l'appel de la méthode `searchPatient` est stocké dans la variable **`pagePatients`**. Cette variable
contient
une Page d'objets Patient qui correspondent aux critères de recherche spécifiés par le `keyword`, et aux
informations de page spécifiées par le `PageRequest`.

```java
 @GetMapping("/user/index")
public String patients(Model model,@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "5") int size,@RequestParam(name = "keyword", defaultValue = "") String keyword){

        Page<Patient> pagePatients=iHospitalService.searchPatient(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);

        return"patients";
        }
```

- #### Supprimer un patient.

Pour supprimer un patient, on doit envoyer une requete `GET` sur l'URL `http://localhost:8080/admin/deletePatient` avec
les parametres `id`, `page` et `keyword` pour supprimer le patient avec l'identifiant `id`, en utilisant la methode
`deletePatient`.

```java
@GetMapping("/admin/deletePatient")
public String deletePatient(Model model,@RequestParam(name = "id") Long id,@RequestParam(name = "page") int page,@RequestParam(name = "keyword", defaultValue = "") String keyword){
        iHospitalService.deletePatient(id);
        model.addAttribute("keyword",keyword);
        return"redirect:/user/index?page="+page;
        }
```    

- #### Faire des améliorations supplémentaires.

la methode pour la mise a jour des informations d'un patient.

```java
@PostMapping("/admin/updatePatient")
public String updatePatient(@RequestParam Long id,@RequestParam String name,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate,@RequestParam Boolean sick,@RequestParam int score){
        Patient patient=patientRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid patient Id:"+id));
        patient.setName(name);
        patient.setBirthDate(birthdate);
        patient.setSick(sick);
        patient.setScore(score);
        patientRepository.updatePatient(patient);
        return"redirect:/user/index";
        }
```

![update](/img/update.png)

## - Partie 2 : Template et la validation du formulaire

### la validation dans le formulaire de l'ajout d'un patient.

![update](/img/validationForm.png)

## - Partie 3 : La sécurité de l'application avec <u>Spring Security</u>

#### la Gestion des rôles

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/",true).permitAll();
        httpSecurity.rememberMe();
        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();
        }
```

#### la page de Login

![update](/img/login.png)

### la page de l'acces non autorisé

![update](/img/notAuthorized.png)