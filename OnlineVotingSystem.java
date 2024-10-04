import java.util.*;

public class OnlineVotingSystem {
    
    // Maps to store registered voters and candidates
    private static Map<String, Voter> voters = new HashMap<>();
    private static Map<String, Candidate> candidates = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Registration of sample candidates
        candidates.put("1", new Candidate("1", "Candidate A"));
        candidates.put("2", new Candidate("2", "Candidate B"));
        candidates.put("3", new Candidate("3", "Candidate C"));
        
        // User Registration or Login
        System.out.println("Welcome to the Online Voting System!");
        System.out.println("1. Register as Voter");
        System.out.println("2. Login as Voter");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (choice == 1) {
            // Voter registration
            System.out.print("Enter Voter ID: ");
            String voterID = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            if (voters.containsKey(voterID)) {
                System.out.println("Voter already registered.");
            } else {
                voters.put(voterID, new Voter(voterID, password));
                System.out.println("Voter registered successfully!");
            }
        }
        
        // Voter login
        System.out.print("Enter Voter ID: ");
        String voterID = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        if (authenticateVoter(voterID, password)) {
            boolean voted = false;
            while (!voted) {
                // Display ballot and allow voting
                System.out.println("\nElectronic Ballot");
                for (Candidate candidate : candidates.values()) {
                    System.out.println(candidate.getCandidateID() + ". " + candidate.getName());
                }
                System.out.print("Enter Candidate ID to vote: ");
                String candidateID = scanner.nextLine();
                
                if (candidates.containsKey(candidateID)) {
                    candidates.get(candidateID).addVote();
                    voted = true;
                    System.out.println("Vote cast successfully!");
                } else {
                    System.out.println("Invalid Candidate ID. Try again.");
                }
            }
        } else {
            System.out.println("Authentication failed.");
        }
        
        // Display results
        System.out.println("\nElection Results:");
        for (Candidate candidate : candidates.values()) {
            System.out.println(candidate.getName() + " - Votes: " + candidate.getVotes());
        }
        
        scanner.close();
    }

    // Voter authentication method
    private static boolean authenticateVoter(String voterID, String password) {
        if (voters.containsKey(voterID)) {
            Voter voter = voters.get(voterID);
            return voter.getPassword().equals(password);
        }
        return false;
    }
}

// Voter class
class Voter {
    @SuppressWarnings("unused")
    private String voterID;
    private String password;
    
    public Voter(String voterID, String password) {
        this.voterID = voterID;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

// Candidate class
class Candidate {
    private String candidateID;
    private String name;
    private int votes;

    public Candidate(String candidateID, String name) {
        this.candidateID = candidateID;
        this.name = name;
        this.votes = 0;
    }

    public String getCandidateID() {
        return candidateID;
    }

    public String getName() {
        return name;
    }

    public void addVote() {
        votes++;
    }

    public int getVotes() {
        return votes;
    }
}
