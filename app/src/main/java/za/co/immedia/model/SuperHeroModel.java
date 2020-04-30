package za.co.immedia.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SuperHeroModel {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("results-for")
    @Expose
    private String resultsFor;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResultsFor() {
        return resultsFor;
    }

    public void setResultsFor(String resultsFor) {
        this.resultsFor = resultsFor;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }



    public class Appearance {

        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("race")
        @Expose
        private String race;
        @SerializedName("height")
        @Expose
        private List<String> height = null;
        @SerializedName("weight")
        @Expose
        private List<String> weight = null;
        @SerializedName("eye-color")
        @Expose
        private String eyeColor;
        @SerializedName("hair-color")
        @Expose
        private String hairColor;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public List<String> getHeight() {
            return height;
        }

        public void setHeight(List<String> height) {
            this.height = height;
        }

        public List<String> getWeight() {
            return weight;
        }

        public void setWeight(List<String> weight) {
            this.weight = weight;
        }

        public String getEyeColor() {
            return eyeColor;
        }

        public void setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
        }

        public String getHairColor() {
            return hairColor;
        }

        public void setHairColor(String hairColor) {
            this.hairColor = hairColor;
        }

    }


    public class Biography {

        @SerializedName("full-name")
        @Expose
        private String fullName;
        @SerializedName("alter-egos")
        @Expose
        private String alterEgos;
        @SerializedName("aliases")
        @Expose
        private List<String> aliases = null;
        @SerializedName("place-of-birth")
        @Expose
        private String placeOfBirth;
        @SerializedName("first-appearance")
        @Expose
        private String firstAppearance;
        @SerializedName("publisher")
        @Expose
        private String publisher;
        @SerializedName("alignment")
        @Expose
        private String alignment;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getAlterEgos() {
            return alterEgos;
        }

        public void setAlterEgos(String alterEgos) {
            this.alterEgos = alterEgos;
        }

        public List<String> getAliases() {
            return aliases;
        }

        public void setAliases(List<String> aliases) {
            this.aliases = aliases;
        }

        public String getPlaceOfBirth() {
            return placeOfBirth;
        }

        public void setPlaceOfBirth(String placeOfBirth) {
            this.placeOfBirth = placeOfBirth;
        }

        public String getFirstAppearance() {
            return firstAppearance;
        }

        public void setFirstAppearance(String firstAppearance) {
            this.firstAppearance = firstAppearance;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getAlignment() {
            return alignment;
        }

        public void setAlignment(String alignment) {
            this.alignment = alignment;
        }

    }

   public class Connections {

        @SerializedName("group-affiliation")
        @Expose
        private String groupAffiliation;
        @SerializedName("relatives")
        @Expose
        private String relatives;

        public String getGroupAffiliation() {
            return groupAffiliation;
        }

        public void setGroupAffiliation(String groupAffiliation) {
            this.groupAffiliation = groupAffiliation;
        }

        public String getRelatives() {
            return relatives;
        }

        public void setRelatives(String relatives) {
            this.relatives = relatives;
        }

    }


    public class Image {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }


    public class Powerstats {

        @SerializedName("intelligence")
        @Expose
        private String intelligence;
        @SerializedName("strength")
        @Expose
        private String strength;
        @SerializedName("speed")
        @Expose
        private String speed;
        @SerializedName("durability")
        @Expose
        private String durability;
        @SerializedName("power")
        @Expose
        private String power;
        @SerializedName("combat")
        @Expose
        private String combat;

        public String getIntelligence() {
            return intelligence;
        }

        public void setIntelligence(String intelligence) {
            this.intelligence = intelligence;
        }

        public String getStrength() {
            return strength;
        }

        public void setStrength(String strength) {
            this.strength = strength;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDurability() {
            return durability;
        }

        public void setDurability(String durability) {
            this.durability = durability;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getCombat() {
            return combat;
        }

        public void setCombat(String combat) {
            this.combat = combat;
        }

    }


    public class Result {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("powerstats")
        @Expose
        private Powerstats powerstats;
        @SerializedName("biography")
        @Expose
        private Biography biography;
        @SerializedName("appearance")
        @Expose
        private Appearance appearance;
        @SerializedName("work")
        @Expose
        private Work work;
        @SerializedName("connections")
        @Expose
        private Connections connections;
        @SerializedName("image")
        @Expose
        private Image image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Powerstats getPowerstats() {
            return powerstats;
        }

        public void setPowerstats(Powerstats powerstats) {
            this.powerstats = powerstats;
        }

        public Biography getBiography() {
            return biography;
        }

        public void setBiography(Biography biography) {
            this.biography = biography;
        }

        public Appearance getAppearance() {
            return appearance;
        }

        public void setAppearance(Appearance appearance) {
            this.appearance = appearance;
        }

        public Work getWork() {
            return work;
        }

        public void setWork(Work work) {
            this.work = work;
        }

        public Connections getConnections() {
            return connections;
        }

        public void setConnections(Connections connections) {
            this.connections = connections;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

    }

    public class Work {

        @SerializedName("occupation")
        @Expose
        private String occupation;
        @SerializedName("base")
        @Expose
        private String base;

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

    }
}