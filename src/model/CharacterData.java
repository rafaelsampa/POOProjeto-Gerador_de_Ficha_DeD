package model;

public class CharacterData {
    private String name;
    private RPGClass rpgClass;
    private RPGRace rpgRace;

    public CharacterData(RPGClass rpgClass, RPGRace rpgRace, String name) {
        this.rpgClass = rpgClass;
        this.rpgRace = rpgRace;
        this.name = name;
    }

    public RPGClass getRpgClass() {
        return rpgClass;
    }

    public RPGRace getRpgRace() {
        return rpgRace;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CharacterData{" +
                "name='" + name + '\'' +
                ", rpgClass=" + rpgClass +
                ", rpgRace=" + rpgRace +
                '}';
    }
}