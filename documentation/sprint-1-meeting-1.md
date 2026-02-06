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

- [x] Take a screenshot of kanban and files and upload to gradescope

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
- [x] decide what user stories fit for the sprint (add more if need to)
    - break down into sub-tasks if to big for one person to add in a few hours
    - add them to sprint 1 area (tag as sprint 1)
- [x] add functional req's for sprint 
    - database
    - ui

#### Structure
##### Questions
- Ktor materials / confidence ? 
	- 
- What data do we need?  
	-  Books/copies/users/reservations/status(es)
- How will data be stored and what does that look like?  
	- database (h2)
	- ![[Pasted image 20260206101233.png|500]]
- What kind of classes might we need?  
	- 
- What routes will we need?
	-  
Get (/) 
get (/reservations)
post(/reservations)  // mark as returned
get(/search?q= )
post(/search) 
get(/login) 
post(/login)
get(/account)
post(/account)  // change details
get(/details/ISBN) 
post(/details/ISBN)  // check out

(no post for account for scope creep)

##### Decisions
- [x] decide database layout (create in dbdiagram.io, limit to only stuff needed for sprint)
- [x] decide on DoD and each add names to bottom 
	- suggestions in the PR
	- review changes 
	- then merge to main
- [x] decide css framework (bootstrap / ~~pico.css~~)
- [x] decide tech details (pebble(or ~~kotlinx.html~~ /amper(or gradlew))
	- Generating HTML (~~DSL~~  / Pebble)
	- Accessing Datbase
		- Exposed Library 
		- Store Database (H2 / ~~sqlite~~)
		- Access Database in code (SQL DSL vs DAO API)

##### Layout / Design
- [x] Wireframing
    - list pages 
    - sketch them out on https://draw.io
		- What will be on the page?
		- Where it will be?
		- How pages link together & user flow
		- add colours swatch and fonts to the wireframe image  
	- ![[Pasted image 20260206114503.png]]
- [x] <i style="font-family:Comic Sans MS; background: linear-gradient(90deg, red, orange, yellow, green, cyan, blue, violet); background-clip: text; color: transparent;">graphic design is my passion</i>
    - decide colours https://coolors.co ( [Accessibility](https://coolors.co/contrast-checker/112a46-acc8e5) ) 
    - ```
	https://coolors.co/f4f6f6-dde3e3-242e2d-475b5a-d7acc0-8cb369-c81d25
      background  #F4F6F6 
	text colour (h1-6)  #242E2D
	text colour (content) #475B5A
	alt background #DDE3E3 

	yes (#8CB369)
	no (#C81D25)  accent buttons
	accent colour  #D7ACC0
      ```
![[Pasted image 20260206114514.png]]
	- [x] pick fonts ( Sans Serif vs Serif vs Mix ) 
		  - <span style="font-family: Inter, 'Comic Sans MS';">Inter</span> / <span style="font-family: Roboto, 'Comic Sans MS';">Roboto</span> / <span style="font-family: Noto Sans, 'Comic Sans MS';">Noto Sans</span>
		  - <span style="font-family: 'Playfair','Playfair 12pt Light', 'Comic Sans MS';">Playfair </span> / <span style="font-family: 'Playfair Display', 'Comic Sans MS';">Playfair Display</span> / <span style="font-family: 'Roboto Slab', 'Comic Sans MS';">Roboto Slab</span> / <span style="font-family: 'Roboto Serif','Roboto Serif 14pt', 'Comic Sans MS'">Roboto Serif</span>
		  - https://fonts.google.com/
		  - serif for `h1-6` 
			  - Playfair display
		  - sans-serif for `<p>` 
			  - Atkinson Hyperlegible (from google fonts cdn)
			  - 


### Assigning Responsibilities
- [x] allocate sprint user stories to people (in github)
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
