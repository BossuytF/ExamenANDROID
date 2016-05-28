package com.example.fabrice.recyclerview;

public class Character {

    public int imageId;
    public String characterName;
    public String characterDescription;

    public Character(int imageId, String characterName, String characterDescription) {
        this.imageId = imageId;
        this.characterName = characterName;
        this.characterDescription = characterDescription;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }

}

