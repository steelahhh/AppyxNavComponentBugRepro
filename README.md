# AppyxNavComponentBugRepro
Repro project for an issue found with integrating Appyx into androidx.navigation with Fragments

## Issue
Appyx `BackHandler`s are ignored after the app comes into foreground (close/open the app, lock/unlock screen, etc) 

## Steps to reproduce

### In code: 
* Define a androidx.navigation NavGraph with 2+ Fragments 
* Host Appyx tree with any `NavModel` (`BackStack` in this case) in the non-root Fragment

### Runtime: 
* Navigate from root to Appyx fragment
* Push some nodes to `BackStack`
* Press "Home" (or lock screen)
* Open the app 
* Press back button
* Appyx `BackHandler`s are ignored
