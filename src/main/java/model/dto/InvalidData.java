package model.dto;

public class InvalidData {
    private String invalidLogin;
    private String invalidEmail;
    private String invalidPassword;
    private String invalidFirstName;
    private String invalidFirstNameEn;
    private String invalidLastName;
    private String invalidLastNameEn;
    private String invalidTitle;
    private String invalidTitleEn;
    private String invalidDescription;
    private String invalidDescriptionEn;
    private String invalidConfTitle;
    private String invalidConfTitleEn;
    private String invalidConfDescription;
    private String invalidConfDescriptionEn;
    private String invalidConfPlace;
    private String invalidConfPlaceEn;
    private String invalidConfDateTime;
    private String invalidConfLecturesCapacity;
    private String invalidConfPlaceCapacity;
    private String invalidLectureStartTime;


    private InvalidData() {

    }

    public String getInvalidLectureStartTime() {
        return invalidLectureStartTime;
    }

    public String getInvalidConfTitle() {
        return invalidConfTitle;
    }

    public String getInvalidConfTitleEn() {
        return invalidConfTitleEn;
    }

    public String getInvalidConfDescription() {
        return invalidConfDescription;
    }

    public String getInvalidConfDescriptionEn() {
        return invalidConfDescriptionEn;
    }

    public String getInvalidConfPlace() {
        return invalidConfPlace;
    }

    public String getInvalidConfPlaceEn() {
        return invalidConfPlaceEn;
    }

    public String getInvalidConfDateTime() {
        return invalidConfDateTime;
    }

    public String getInvalidConfLecturesCapacity() {
        return invalidConfLecturesCapacity;
    }

    public String getInvalidConfPlaceCapacity() {
        return invalidConfPlaceCapacity;
    }

    public String getInvalidTitle() {
        return invalidTitle;
    }

    public String getInvalidTitleEn() {
        return invalidTitleEn;
    }

    public String getInvalidDescription() {
        return invalidDescription;
    }

    public String getInvalidDescriptionEn() {
        return invalidDescriptionEn;
    }

    public String getInvalidFirstNameEn() {
        return invalidFirstNameEn;
    }

    public String getInvalidLastNameEn() {
        return invalidLastNameEn;
    }

    public String getInvalidLogin() {
        return invalidLogin;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }

    public String getInvalidFirstName() {
        return invalidFirstName;
    }

    public String getInvalidLastName() {
        return invalidLastName;
    }

    public static Builder newBuilder(String attribute) {
        return new InvalidData().new Builder(attribute);
    }

    public class Builder {

        private final String attribute;

        private Builder(String attribute) {
            this.attribute = attribute;
        }

        public Builder setInvalidLoginAttr() {
            InvalidData.this.invalidLogin = attribute;
            return this;
        }

        public Builder setInvalidEmailAttr() {
            InvalidData.this.invalidEmail = attribute;
            return this;
        }

        public Builder setInvalidFirstNameAttr() {
            InvalidData.this.invalidFirstName = attribute;
            return this;
        }

        public Builder setInvalidFirstNameEnAttr() {
            InvalidData.this.invalidFirstNameEn = attribute;
            return this;
        }

        public Builder setInvalidPasswordAttr() {
            InvalidData.this.invalidPassword = attribute;
            return this;
        }

        public Builder setInvalidLastNameAttr() {
            InvalidData.this.invalidLastName = attribute;
            return this;
        }

        public Builder setInvalidLastNameEnAttr() {
            InvalidData.this.invalidLastNameEn = attribute;
            return this;
        }

        public Builder setInvalidTitleAttr() {
            InvalidData.this.invalidTitle = attribute;
            return this;
        }

        public Builder setInvalidTitleEnAttr() {
            InvalidData.this.invalidTitleEn = attribute;
            return this;
        }

        public Builder setInvalidDescriptionAttr() {
            InvalidData.this.invalidDescription = attribute;
            return this;
        }

        public Builder setInvalidDescriptionEnAttr() {
            InvalidData.this.invalidDescriptionEn = attribute;
            return this;
        }

        public Builder setInvalidConfTitleAttr() {
            InvalidData.this.invalidConfTitle = attribute;
            return this;
        }

        public Builder setInvalidConfTitleEnAttr() {
            InvalidData.this.invalidConfTitleEn = attribute;
            return this;
        }

        public Builder setInvalidConfDescriptionAttr() {
            InvalidData.this.invalidConfDescription = attribute;
            return this;
        }

        public Builder setInvalidConfDescriptionEnAttr() {
            InvalidData.this.invalidConfDescriptionEn = attribute;
            return this;
        }

        public Builder setInvalidConfPlaceAttr() {
            InvalidData.this.invalidConfPlace = attribute;
            return this;
        }

        public Builder setInvalidConfPlaceEnAttr() {
            InvalidData.this.invalidConfPlaceEn = attribute;
            return this;
        }

        public Builder setInvalidConfDateTimeAttr() {
            InvalidData.this.invalidConfDateTime = attribute;
            return this;
        }

        public Builder setInvalidConfLecturesCapacityAttr() {
            InvalidData.this.invalidConfLecturesCapacity = attribute;
            return this;
        }

        public Builder setInvalidConfPlaceCapacityAttr() {
            InvalidData.this.invalidConfPlaceCapacity = attribute;
            return this;
        }

        public Builder setInvalidLectureStartTimeAttr() {
            InvalidData.this.invalidLectureStartTime = attribute;
            return this;
        }

        public InvalidData build() {
            return InvalidData.this;
        }
    }
}


