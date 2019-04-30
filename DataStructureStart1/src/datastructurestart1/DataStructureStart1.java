package datastructurestart1;
import java.util.*;

import java.io.File;
import java.util.Scanner;

public class DataStructureStart1 {
    public static final String DATA_FILE = "data.txt";

    public static void main(String[] args) { 
        TreeMap<String, TreeSet<String>> comp_map = new TreeMap<String, TreeSet<String>>();
          
        Scanner in = null;
        
        try {
            in = new Scanner(new File(DATA_FILE));
            int count = 0;
            
            while (in.hasNext()) {
                String liney = in.nextLine();
                String [] lineArr = liney.split(" ");
                String empl = lineArr[0];
                String comp = lineArr[2];
                  
                TreeSet<String> company = comp_map.get(comp);
                  
                if(company == null) {
                    company = new TreeSet<String>();
                    comp_map.put(comp,company);
                }
                comp_map.get(comp).add(empl);
            }
            
            System.out.println(comp_map);
            
            Set<String> set = comp_map.keySet();
            Iterator<String> iter = set.iterator();
            
            int max = 0;
            String max_comp = " ";
            /*
            for (int k = 0; k < set.size(); ++k) {
               String company = iter.next();
               if (max < comp_map.get(company).size()) {
                   max = comp_map.get(company).size();
                   max_comp = company;
               }
            }
            */
  
            for (int k = 0; k < set.size(); ++k) {
               String company = iter.next();
               if (max < comp_map.get(company).size()) {
                   max = comp_map.get(company).size();
                   max_comp = company;
               }
            }
            
            System.out.println(String.format("The company with most emps is %s with %d emps", max_comp, max));
            
            // System.out.println("The company with the most employees is " + max_comp + " with " + max " employees.");
           
        }
        catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
        
        TreeSet<Employee> emps = new TreeSet<Employee>();
        emps.add(new Employee("Sue", 100.0));
        emps.add(new Employee("Alan", 200.0));
        emps.add(new Employee("Bob", 300.0));
        System.out.println(emps);
        
        
    }
}
class Employee implements Comparable<Employee>
{
   private String name;
   private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", salary=" + salary + '}';
    }

   

    @Override
    public int compareTo(Employee t) {
       return this.name.compareTo(t.name);
    }
    
    
}
/*
data.txt

The cat was running down the path, 
through the forest.  The day was just
a bit rainy, and the ground was 
dark and slippery.

*/

/*

package datastructurestart1;
import java.util.*;

import java.io.File;
import java.util.Scanner;

public class DataStructureStart1 {
    public static final String DATA_FILE = "data.txt";

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(19);
        
        HashSet<String> hash =  new HashSet<String>();
        hash.add("this");
        
        TreeSet<Integer> tree = new TreeSet<Integer>();
        
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Alan", 57);
        
        if (hashMap.containsKey("Alan"))
            System.out.println("Found");
        
        int i = hashMap.get("Alan");
            
       
        Scanner in = null;
        
        try {
            in = new Scanner(new File(DATA_FILE));
            int count = 0;
            
            while (in.hasNext()) {
                String word = in.next();  // or in.nextLine, in.nextDouble, in.nextInt
                // System.out.println("READ: " + word);
                if (!hash.contains(word)) {
                    hash.add(word);
                    count++;
                }
            }
            System.out.println("There are " + count + " word(s)");
        }
        catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
    }
}

/*
data.txt

The cat was running down the path, 
through the forest.  The day was just
a bit rainy, and the ground was 
dark and slippery.

*/

/*

        try {
            in = new Scanner(new File(DATA_FILE));
            int count = 0;
            
            while (in.hasNext()) {
                String liney = in.nextLine();
                String [] lineArr = liney.split(" ");
                for (int k = 0; k < lineArr.length; k++) {
                  if(!lineNum.containsKey(lineArr[k])) { 
                      TreeSet<Integer> numTree = new TreeSet<Integer>();
                      numTree.add(count);
                      lineNum.put(lineArr[k].trim().toLowerCase(), numTree);
                  }
                  else {
                      lineNum.get(lineArr[k]).add(count);
                  }
                }
                count++;
            }
            System.out.println("There are " + lineNum.size() + " words");
            
            // Print out in alphabetical order
            System.out.println("Here are our words! " + lineNum.toString());
        }
*/

/*
        try {
            in = new Scanner(new File(DATA_FILE));
            int count = 0;
            
            while (in.hasNext()) {
                String liney = in.nextLine();
                String [] lineArr = liney.split(" ");
                for (String word : lineArr) { 
                  word = word.trim().toLowerCase();
                  if(!lineNum.containsKey(word)) { 
                      TreeSet<Integer> numTree = new TreeSet<Integer>();
                      numTree.add(count);
                      lineNum.put(word, numTree);
                  }
                  else {
                      lineNum.get(word).add(count);
                  }
                }
                count++;
            }
            System.out.println("There are " + lineNum.size() + " words");
            
            // Print out in alphabetical order
            System.out.println("Here are our words! " + lineNum.toString());
        }
*/

/*
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(19);
        
        HashMap<String, HashSet<Integer>> fancy = new HashMap<String, HashSet<Integer>>();
        fancy.put("Alan", new HashSet<Integer>());
        
        TreeMap<String, TreeSet<Integer>> lineNum = new TreeMap<String, TreeSet<Integer>>();
        
        HashSet<String> hash =  new HashSet<String>();
        //  hash.add("this");
        
        TreeSet<String> tree = new TreeSet<String>();
        
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Alan", 57);
        
        if (hashMap.containsKey("Alan"))
            System.out.println("Found");
        
        int i = hashMap.get("Alan");
        
        String line = "the cat ran down the road!";
        String [] words = line.split(" ");
        for (int j = 0; j < words.length; j++)
                System.out.println(words[j]);
        
        for (String word : words)
            System.out.println(word);
        
        
        
        
            
       
        Scanner in = null;
        
        try {
            in = new Scanner(new File(DATA_FILE));
            int count = 0;
            
            while (in.hasNext()) {
                String liney = in.nextLine();
                String [] lineArr = liney.split(" ");
                for (String word : lineArr) { 
                  word = word.trim().toLowerCase();
                  
                  TreeSet<Integer> nums = lineNum.get(word);
                  
                  if(nums == null) 
                      nums = new TreeSet<Integer>();
         
                  lineNum.get(word).add(count);
                }
                count++;
            }
            System.out.println("There are " + lineNum.size() + " words");
            
            // Print out in alphabetical order
            System.out.println("Here are our words! " + lineNum.toString());
        }
        catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
*/


