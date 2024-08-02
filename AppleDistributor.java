// Since there were no instructions regarding whom to allocate the apples if the exact amount of apple is not been able to distribute wheather I need to give to the person who has paid the highest of the lowest , So what I did is sorted the apples array and allocate apples accordingly which benifited the person who had paid the highest and in this case the person who has paid the lowest will have to suffer and will get apples lesser than what he has paid for, I could have even do this opposite , I didn't got any other way to solve the problem so I did. 


// Approach: The initial thought came to my mid is traditional knapsack problem but it doesn't involve maximizing value but the idea for distributing weights was similar, Then further I though for backtracting but didn't got to the exact solution. Then furher I simply sorted the array and started allocating apples greedily.

import java.util.*;

public class AppleDistributor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> appleWeights = new ArrayList<>();
        
        System.out.println("Enter apple weight in grams (-1 to stop):");
        while (true) {
            int weight = scanner.nextInt();
            if (weight == -1) {
                break;
            }
            appleWeights.add(weight);
        }
        
        int ramPaid = 50;
        int shamPaid = 30;
        int rahimPaid = 20;
        int totalPayment = ramPaid + shamPaid + rahimPaid;
        
        int totalWeight = 0;
        for (int weight : appleWeights) {
            totalWeight += weight;
        }
        
        int ramTargetWeight = (ramPaid * totalWeight) / totalPayment;
        int shamTargetWeight = (shamPaid * totalWeight) / totalPayment;
        int rahimTargetWeight = (rahimPaid * totalWeight) / totalPayment;
        
        Collections.sort(appleWeights, Collections.reverseOrder());
        
        List<Integer> ramApples = new ArrayList<>();
        List<Integer> shamApples = new ArrayList<>();
        List<Integer> rahimApples = new ArrayList<>();
        
        for (int weight : appleWeights) {
            if (sumList(ramApples) + weight <= ramTargetWeight) {
                ramApples.add(weight);
            } else if (sumList(shamApples) + weight <= shamTargetWeight) {
                shamApples.add(weight);
            } else if (sumList(rahimApples) + weight <= rahimTargetWeight) {
                rahimApples.add(weight);
            } else {
                int ramDiff = Math.abs(sumList(ramApples) + weight - ramTargetWeight);
                int shamDiff = Math.abs(sumList(shamApples) + weight - shamTargetWeight);
                int rahimDiff = Math.abs(sumList(rahimApples) + weight - rahimTargetWeight);
                
                if (ramDiff <= shamDiff && ramDiff <= rahimDiff) {
                    ramApples.add(weight);
                } else if (shamDiff <= rahimDiff) {
                    shamApples.add(weight);
                } else {
                    rahimApples.add(weight);
                }
            }
        }
        
        System.out.println("Distribution Result:");
        System.out.println("Ram: " + ramApples);
        System.out.println("Sham: " + shamApples);
        System.out.println("Rahim: " + rahimApples);
        
        scanner.close();
    }
    
    private static int sumList(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
}

