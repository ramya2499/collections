package com.tgt.igniteplus;

/*
1) User Should be able to Create, Delete and Update the Department.
2) Each and every department should have at least 1 Ignite Member
3) Department can have more than 1 Ignite Member
Features:
1) Swapping of Ignite Members from one dept to another dept
2) Add a new Skillset to all the Ignite members for the specific dept
3) List all the users who have the Skill of “Java” with department name
4) Make Sure unique Ignite Members are added into the department (Assuming the
unique attribute is Name)
5) Validations at all operation is mandatory
*/

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class mainpgm {

        static List<ignitemem> memberObj = new CopyOnWriteArrayList<>();
        static List<String> Department = new ArrayList<>();
        static Set<String> SkillSet = new HashSet<>();
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int option;

            Set<String> gauthamSkillSet = new HashSet<>();
            gauthamSkillSet.add("Java");
            gauthamSkillSet.add("SQL");
            gauthamSkillSet.add("DS");

            Set<String> divyaSkillSet = new HashSet<>();
            divyaSkillSet.add("Java");
            divyaSkillSet.add("NOSQL");
            divyaSkillSet.add("ML");

            Set<String> amitSkillSet = new HashSet<>();
            amitSkillSet.add("Linux");
            amitSkillSet.add("PSQL");
            amitSkillSet.add("Scripting");

            Set<String> naveenSkillSet = new HashSet<>();
            naveenSkillSet.add("Chef");
            naveenSkillSet.add("React");
            naveenSkillSet.add("AI");

            Department.add("Data Science");
            Department.add("Infrastructure");

            memberObj.add(new ignitemem("Gautham", "VTU", Department.get(0), gauthamSkillSet, 28));
            memberObj.add(new ignitemem("Divya", "TGT", Department.get(0), divyaSkillSet, 26));
            memberObj.add(new ignitemem("Amit", "TMT", Department.get(1), amitSkillSet, 25));
            memberObj.add(new ignitemem("Naveen", "DOJO", Department.get(1), naveenSkillSet, 22));

            do {
                System.out.println("\n-------------------------------------------------\n" +
                        "Option Menu:\n" +
                        "1. Display list of Departments\n" +
                        "2. Create a new Department\n" +
                        "3. Delete a Department\n" +
                        "4. Display all Members based on departments\n" +
                        "5. Create a Member and add to a department\n" +
                        "6. Display members based on given skill\n" +
                        "7. Swap department of a member\n" +
                        "8. Add new skill set to all members of a department\n" +
                        "-------------------------------------------------");
                System.out.print("Enter your option:\t");
                int choice = in.nextInt();
                switch (choice) {
                    case 1: displayDept();
                        break;
                    case 2: String department = createDept();
                        System.out.println("Now enter a member in this department:");
                        createMember(department);
                        break;
                    case 3: removeDept();
                        break;
                    case 4: displayMemberDeptWise();
                        break;
                    case 5: createMember(null);
                        break;
                    case 6: displayMemberSkillWise();
                        break;
                    case 7: swapDept();
                        break;
                    case 8: addNewSkill();
                        break;
                    default:
                        System.out.print("Invalid option!\n");
                }
                System.out.print("-------------------------------------------------\n" +
                        "Do you want to continue? (0/1):\t");
                option = in.nextInt();
            } while (option == 1);
            System.exit(0);
        }

        private static void addNewSkill() {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the new skill:\t");
            String newSkill = in.next();
            System.out.print("Enter the department choice:\n");
            int j = 1;
            for (String deptObj : Department) {
                System.out.println(j + ". " + deptObj);
                j++;
            }
            String dept = null;
            int deptChoice = in.nextInt();
            int k = 1;
            for (String deptObj : Department) {
                if (k == deptChoice) {
                    dept = deptObj;
                    break;
                }
                k++;
            }
            for (ignitemem im : memberObj) {
                if (im.getDepartment().contains(dept)) {
                    Set<String> skill = im.getSkillSet();
                    skill.add(newSkill);
                    im.setSkillSet(skill);
                }
            }
            System.out.println("Added!");
            for (ignitemem im : memberObj)
                if (im.getDepartment().contains(dept))
                    System.out.println("Name:\t" + im.getName() + "\t\t\tSkills:\t" + im.getSkillSet());
        }

        private static void swapDept() {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the name of the member who's department you want to change:\t");
            String memName = in.next();
            System.out.println("Enter the choice of department:");
            int j = 1;
            for (String deptObj : Department) {
                System.out.println(j + ". " + deptObj);
                j++;
            }
            String dept = null;
            int deptChoice = in.nextInt();
            int k = 1;
            for (String deptObj : Department) {
                if (k == deptChoice) {
                    dept = deptObj;
                    break;
                }
                k++;
            }
            for (ignitemem im : memberObj) {
                if (im.getName().contains(memName))
                    im.setDepartment(dept);
            }
        }

        private static void createMember(String deptParam) {
            Scanner in = new Scanner(System.in);
            Set<String> SkillSet = new HashSet<>();
            String newName, newCollege;
            String dept = deptParam;
            int newAge, count, deptChoice;
            System.out.print("\nEnter the Name of the member:\t");
            newName = in.next();
            do {
                for (ignitemem im : memberObj) {
                    if (im.getName().contains(newName)) {
                        System.out.print("\nName already exists!\n" + "Enter a UNIQUE name:\t");
                        newName = in.next();
                    }
                }
            } while (memberObj.contains(newName));
            System.out.print("Enter the Age of the member:\t");
            newAge = in.nextInt();
            System.out.print("Enter the College of the member:\t");
            newCollege = in.next();
            System.out.print("Enter the number of skills:\t");
            count = in.nextInt();
            System.out.print("Enter the Skill Set:\t");
            for (int j = 0; j < count; j++) {
                String newSkill = in.next();
                SkillSet.add(newSkill);
            }
            if (dept == null) {
                System.out.println("\nAdd the member to one of the departments:");
                int j = 1;
                for (String deptObj : Department) {
                    System.out.println(j + ". " + deptObj);
                    j++;
                }
                System.out.print("Enter your option:\t");
                deptChoice = in.nextInt();
                int k = 1;
                for (String deptObj : Department) {
                    if (k == deptChoice) {
                        dept = deptObj;
                        break;
                    }
                    k++;
                }
            }
            memberObj.add(new ignitemem(newName, newCollege, dept, SkillSet, newAge));
            System.out.println("Created!");
        }

        private static void displayMemberSkillWise() {
            Scanner in = new Scanner(System.in);
            System.out.println("List of members according to skill:");
            System.out.print("Enter the skill:\t");
            String skill = in.nextLine();
            System.out.println("\nMembers having " + skill + " skills:");
            for (ignitemem im : memberObj) {
                if (im.getSkillSet().contains(skill))
                    System.out.println(im);
            }
        }

        private static void displayMemberDeptWise() {
            System.out.println("\nDEPARTMENT\t\t\t|\t\tMEMBER NAME\n" + "-------------------------------------------------");
            for(ignitemem mem : memberObj) {
                System.out.println(mem.getDepartment() + "\t\t|\t\t" + mem.getName());
            }
        }

        private static void removeDept() {
            Scanner in = new Scanner(System.in);
            int deptChoice;
            String deleteDept = null;
            System.out.println("\nEnter the department to be deleted:\t");
            int j = 1;
            for (String deptObj : Department) {
                System.out.println(j + ". " + deptObj);
                j++;
            }
            deptChoice = in.nextInt();
            int k = 1;
            for (String deptObj : Department) {
                if (k == deptChoice) {
                    deleteDept = deptObj;
                    break;
                }
                k++;
            }
            for (ignitemem mem : memberObj) {
                if(mem.getDepartment().contains(deleteDept)) {
                    memberObj.remove(mem);
                }
            }
            Department.remove(deleteDept);
            System.out.println("Removed!");
        }

        private static void displayDept() {
            int i = 1;
            System.out.println("\nDepartments:");
            for (String deptObj : Department) {
                System.out.println(i + ". " + deptObj);
                i++;
            }
        }

        private static String createDept() {
            Scanner in = new Scanner(System.in);
            System.out.println("\nEnter the name of the department to be added:\t");
            String newDept = in.next();
            Department.add(newDept);
            return newDept;
        }
    }


/*OUTPUT:
-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	1

Departments:
1. Data Science
2. Infrastructure
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	2

Enter the name of the department to be added:
cloud concepts
Now enter a member in this department:

Enter the Name of the member:	Ramya
Enter the Age of the member:	22
Enter the College of the member:	SMVIT
Enter the number of skills:	1
Enter the Skill Set:	AI
Created!
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	3

Enter the department to be deleted:
1. Data Science
2. Infrastructure
3. cloud
1
Removed!
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	4

DEPARTMENT			|		MEMBER NAME
-------------------------------------------------
Infrastructure		|		Amit
Infrastructure		|		Naveen
cloud		|		Ramya
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	5

Enter the Name of the member:	Naveen

Name already exists!
Enter a UNIQUE name:	Sanvi
Enter the Age of the member:	21
Enter the College of the member:	JSS
Enter the number of skills:	2
Enter the Skill Set:	AI  JAVA

Add the member to one of the departments:
1. Infrastructure
2. cloud
Enter your option:	2
Created!
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	6
List of members according to skill:
Enter the skill:	Java

Members having Java skills:
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	6
List of members according to skill:
Enter the skill:	AI

Members having AI skills:
Ignite Members:
Name: 'Naveen'	College: 'DOJO'	Department: 'Infrastructure'	SkillSet: [Chef, AI, React]	Age: 22
Ignite Members:
Name: 'Ramya'	College: 'SMVIT'	Department: 'cloud'	SkillSet: [AI]	Age: 22
Ignite Members:
Name: 'Sanvi'	College: 'JSS'	Department: 'cloud'	SkillSet: [JAVA, AI]	Age: 21
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	7
Enter the name of the member who's department you want to change:	Amit
Enter the choice of department:
1. Infrastructure
2. cloud
2
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	8
Enter the new skill:	Python
Enter the department choice:
1. Infrastructure
2. cloud
2
Added!
Name:	Amit			Skills:	[Linux, PSQL, Scripting, Python]
Name:	Ramya			Skills:	[AI, Python]
Name:	Sanvi			Skills:	[JAVA, AI, Python]
-------------------------------------------------
Do you want to continue? (0/1):	0
 */