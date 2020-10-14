# READ ME (please) 
message/ instructions to the evaluator

qaChallenge
QA challenge to demonstrate Automatino and qA knowledge
1) ui_test

The framework can be run by maven command:
mvn test -Pdj_main

dj_main is the profile which maps testng.xml , which further contains all the classes.

The framework also contains reporting structure.

Please visit folder -> myStore->reports and open the latest html file generated post mvn run.

The above can be implemented in Jenkins for CI CD.

2) api_tests: Part 1 of the challenge is completed. Please run the GetPost.features file to execute the scenario.

However part 2 ie.: is not completed
POST /posts
1. Save a new post using a userId got by "GET /users" API.

2. When trying to save a new post without the title, API must return an error.

I was not able to understand the objective and what the ask is. If you can explain, I can try.

thanks and regards,
Dinkar Joshi
