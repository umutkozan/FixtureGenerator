package project.maven;

import java.util.ArrayList;
import java.util.List;

public class FixturGenerator {
    // Verilen takımlar için fikstür oluşturur
    public void generateFixture(List<String> teams) {
        // Takım sayısı tekse, "Bay" takımını ekleyerek çift sayıya tamamla
        if (teams.size() % 2 != 0) {
            teams.add("Bay");
        }

        int numOfTeams = teams.size();
        int numOfRounds = numOfTeams - 1;
        int halfSize = numOfTeams / 2;

        List<String> teamsCopy = new ArrayList<>(teams);
        teamsCopy.remove(0);

        int teamsSize = teamsCopy.size();

        for (int round = 0; round < numOfRounds * 2; round++) {
            System.out.println("Round " + (round + 1));
            int teamIdx = round % teamsSize;

            // İlk takım sabit kalır, diğer takımlar döner
            List<String> homeTeams = new ArrayList<>();
            List<String> awayTeams = new ArrayList<>();

            homeTeams.add(teams.get(0));
            awayTeams.add(teamsCopy.get(teamIdx));

            for (int i = 1; i < halfSize; i++) {
                int firstTeam = (round + i) % teamsSize;
                int secondTeam = (round + teamsSize - i) % teamsSize;

                homeTeams.add(teamsCopy.get(firstTeam));
                awayTeams.add(teamsCopy.get(secondTeam));
            }

            // İlk yarı ve ikinci yarı fikstürlerini yazdırma
            if (round < numOfRounds) {
                printRound(homeTeams, awayTeams);
            } else {
                printRound(awayTeams, homeTeams); // İkinci yarı ters çevrilmiş fikstür
            }
        }
    }

    // Bir turdaki maçları yazdırır
    private void printRound(List<String> homeTeams, List<String> awayTeams) {
        for (int i = 0; i < homeTeams.size(); i++) {
            System.out.println(homeTeams.get(i) + " vs " + awayTeams.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<String> teams = new ArrayList<>();
        teams.add("Galatasaray");
        teams.add("Bursaspor");
        teams.add("Fenerbahçe");
        teams.add("Beşiktaş");
        teams.add("Başakşehir");
        teams.add("Trabzonspor");

        FixturGenerator generator = new FixturGenerator();
        generator.generateFixture(teams);
    }
}
