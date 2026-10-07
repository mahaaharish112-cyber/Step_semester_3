import java.util.*;

interface ScoringRule {
    double calculateScore(double idea, double execution, double presentation);
}

class InnovationScoringRule implements ScoringRule {

    @Override
    public double calculateScore(
            double idea,
            double execution,
            double presentation) {

        return (idea * 0.50)
             + (execution * 0.30)
             + (presentation * 0.20);
    }
}

class OpenScoringRule implements ScoringRule {

    @Override
    public double calculateScore(
            double idea,
            double execution,
            double presentation) {

        return (idea + execution + presentation) / 3.0;
    }
}

class Track {
    private String name;
    private ScoringRule scoringRule;

    public Track(String name, ScoringRule scoringRule) {
        this.name = name;
        this.scoringRule = scoringRule;
    }

    public String getName() {
        return name;
    }

    public double calculateScore(
            double idea,
            double execution,
            double presentation) {

        return scoringRule.calculateScore(
            idea,
            execution,
            presentation
        );
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Project {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Score {
    private double idea;
    private double execution;
    private double presentation;
    private double finalScore;

    public Score(
            double idea,
            double execution,
            double presentation,
            double finalScore) {

        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
        this.finalScore = finalScore;
    }

    public double getFinalScore() {
        return finalScore;
    }
}

class Judge {
    private String name;

    public Judge(String name) {
        this.name = name;
    }

    public Score giveScore(
            Project project,
            Track track,
            double idea,
            double execution,
            double presentation,
            boolean resultsPublished) {

        if (resultsPublished) {
            System.out.println(
                "Rescore rejected: Results have already been published."
            );
            return null;
        }

        double finalScore =
            track.calculateScore(
                idea,
                execution,
                presentation
            );

        System.out.println(
            "Score recorded for '" +
            project.getName() + "'."
        );

        return new Score(
            idea,
            execution,
            presentation,
            finalScore
        );
    }
}

class Team {
    private String name;
    private List<Student> members;
    private Track track;
    private Project project;

    public Team(
            String name,
            List<Student> members,
            Track track) {

        this.name = name;
        this.members = members;
        this.track = track;
    }

    public boolean isValidTeamSize() {
        return members.size() >= 2 &&
               members.size() <= 4;
    }

    public boolean containsStudent(Student student) {
        return members.contains(student);
    }

    public void submitProject(Project project) {

        if (this.project != null) {
            System.out.println(
                "Submission failed: Team can submit only one project."
            );
            return;
        }

        this.project = project;

        System.out.println(
            "Project '" +
            project.getName() +
            "' submitted by " +
            name + "."
        );
    }

    public String getName() {
        return name;
    }

    public Track getTrack() {
        return track;
    }

    public Project getProject() {
        return project;
    }
}

class Hackathon {

    enum State {
        OPEN,
        JUDGING,
        PUBLISHED
    }

    private String name;
    private State state;
    private List<Team> teams;
    private Set<Student> registeredStudents;

    public Hackathon(String name) {
        this.name = name;
        this.state = State.OPEN;
        this.teams = new ArrayList<>();
        this.registeredStudents = new HashSet<>();
    }

    public boolean registerTeam(Team team) {

        if (state != State.OPEN) {
            System.out.println(
                "Registration failed: Hackathon is not open."
            );
            return false;
        }

        if (!team.isValidTeamSize()) {
            System.out.println(
                "Registration failed: A team must have 2 to 4 members."
            );
            return false;
        }

        for (Student student : getTeamMembers(team)) {

            if (registeredStudents.contains(student)) {
                System.out.println(
                    "Registration failed: Student " +
                    student.getName() +
                    " already belongs to a team."
                );
                return false;
            }
        }

        teams.add(team);

        for (Student student : getTeamMembers(team)) {
            registeredStudents.add(student);
        }

        System.out.println(
            "Team " +
            team.getName() +
            " registered (" +
            getTeamMembers(team).size() +
            " members, " +
            team.getTrack().getName() +
            " track)."
        );

        return true;
    }

    private List<Student> getTeamMembers(Team team) {

        try {
            java.lang.reflect.Field field =
                Team.class.getDeclaredField("members");

            field.setAccessible(true);

            return (List<Student>) field.get(team);

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void startJudging() {
        state = State.JUDGING;
    }

    public void publishResults() {

        state = State.PUBLISHED;

        System.out.println("Results published.");
    }

    public boolean areResultsPublished() {
        return state == State.PUBLISHED;
    }
}

public class CodeSprintJudgingDesk {

    public static void main(String[] args) {

        // Create tracks
        Track innovation =
            new Track(
                "Innovation",
                new InnovationScoringRule()
            );

        Track open =
            new Track(
                "Open",
                new OpenScoringRule()
            );

        // Create students
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        // Create hackathon
        Hackathon hackathon =
            new Hackathon("Code Sprint");

        // Create ByteBusters team
        Team byteBusters =
            new Team(
                "ByteBusters",
                Arrays.asList(asha, ravi, neha),
                innovation
            );

        // Register ByteBusters
        hackathon.registerTeam(byteBusters);

        // Create invalid SoloCoder team
        Team soloCoder =
            new Team(
                "SoloCoder",
                Arrays.asList(kiran),
                open
            );

        // Register SoloCoder
        hackathon.registerTeam(soloCoder);

        // Submit project
        Project project =
            new Project("SmartAttend");

        byteBusters.submitProject(project);

        // Start judging
        hackathon.startJudging();

        // Judge gives score
        Judge judge = new Judge("Judge 1");

        Score score =
            judge.giveScore(
                project,
                innovation,
                8,
                7,
                9,
                hackathon.areResultsPublished()
            );

        if (score != null) {
            System.out.printf(
                "Final score: %.2f%n",
                score.getFinalScore()
            );
        }

        // Publish results
        hackathon.publishResults();

        // Judge attempts to change score
        judge.giveScore(
            project,
            innovation,
            10,
            7,
            9,
            hackathon.areResultsPublished()
        );
    }
}
