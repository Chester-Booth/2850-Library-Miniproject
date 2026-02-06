# Meeting To-Do

>[!todo] Sprint Goal
> A user can sign up to the website and reserve books.

## Submit Now

- ~10 user stories
- 2-3 stories with acceptance criteria

we are going to use the user stories for the tasks we do for the sprint so maybe extra persona (?) 

1. go through personas and review them
2. go through draft issues and review them

3. make acceptance criteria


- [ ]Take a screenshot of kanban and files and upload to gradescope

--- 
## Sprint 1 
Sprint 1 needs to be complete by 12th Feb
Code able to be written by end of meeting
and some code has to be written by 10th feb (next session)|


### Tasks for this week

####  Portfolio Task (due the 13th)
- [X] Sprint Goal
- [ ] Database Design
- [ ] UI Wireframes

### Planning 

#### Github Projects Kanban Issues
- [ ] decide what user stories fit for the sprint (add more if need to)
    - break down into sub-tasks if to big for one person to add in a few hours
    - add them to sprint 1 area (tag as sprint 1)
- [ ] add functional req's for sprint 
    - database
    - ui

#### Structure
##### Questions
- Ktor materials / confidence ? 
	- 
- What data do we need?  
	- 
- How will data be stored and what does that look like?  
	- 
- What kind of classes might we need?  
	- 
- What routes will we need?
	- 

##### Decisions
- [ ] decide database layout (create in dbdiagram.io, limit to only stuff needed for sprint)
- [ ] decide on DoD and each add names to bottom 
	- suggestions in the PR
	- review changes 
	- then merge to main
- [ ] decide css framework (bootstrap / pico.css)
- [ ] decide tech details (pebble(or kotlinx.html/amper(or gradlew))
	- Generating HTML (DSL / Pebble)
	- Accessing Datbase
		- Exposed Library 
		- Store Database (H2 / sqlite)
		- Access Database in code (SQL DSL vs DAO API)

##### Layout / Design
- [ ] Wireframing
    - list pages 
    - sketch them out on https://draw.io
		- What will be on the page?
		- Where it will be?
		- How pages link together & user flow
		- add colours swatch and fonts to the wireframe image  
- [ ] <i style="font-family:Comic Sans MS; background: linear-gradient(90deg, red, orange, yellow, green, cyan, blue, violet); background-clip: text; color: transparent;">graphic design is my passion</i>
    - decide colours https://coolors.co ( [Accessibility](https://coolors.co/contrast-checker/112a46-acc8e5) ) 
	- [ ] pick fonts ( Sans Serif vs Serif vs Mix ) 
		  - <span style="font-family: Inter, 'Comic Sans MS';">Inter</span> / <span style="font-family: Roboto, 'Comic Sans MS';">Roboto</span> / <span style="font-family: Noto Sans, 'Comic Sans MS';">Noto Sans</span>
		  - <span style="font-family: 'Playfair', 'Comic Sans MS';">Playfair </span> / <span style="font-family: 'Playfair Display', 'Comic Sans MS';">Playfair Display</span> / <span style="font-family: 'Roboto Slab', 'Comic Sans MS';">Roboto Slab</span> / <span style="font-family: 'Roboto Serif', 'Comic Sans MS'">Roboto Serif</span>
		  - https://fonts.google.com/


### Assigning Responsibilities
- [ ] allocate sprint user stories to people (in github)
- [ ]  setup environment (merge) and get initial branches made for stories
	- [ ] setup github actions 


#### Teamwork
##### PRs
- give time for reviews and changes
	- [ ] github actions to run `./amper build` and `./amper test` and `./gradlew ktlintCheck` and `/gradlew detekt` 
		- ensures no linting / build errors and tests pass
-  final deadline is Friday the 13th but demo(??) needs to be made for then 
- [ ] what should the deadlines for the issues to have PRs / been merged to main be?  
	- PRs by: `  /02/26`
	- Merged by: `  /02/26`
##### Meetings
- [ ] do PRs in person ? 
- [ ] meeting next week? 

---
now its just doing the implementation
