## Dependency Injection with Dagger 2

This project was used as a reference while creating the [presentation](http://www.slideshare.net/EdsonMenegatti/dagger-2-injeo-de-dependncia) on "Dagger 2 - Dependency Injection" featured on the first Android Meetup in Florianopolis/SC.
The code follows the MVP pattern and uses Retrofit for network requests and DBFlow as a ORM.

### Branches
Branches follow the order of slides in the presentation and are described as follows:

  - `master`: sample app without any automatic dependency injection;
  - `dependency_injection`: app now integrated with dagger 2, but with a single component comprehending all modules;
  - `dagger_component_dependency`: example showing usage of component dependency;
  - `dagger_subcomponent`: example of usage of subcomponents.
