package POO;

class Student {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String bacId;
    private String bacInfo;
    private String univCycle;
    private String emailAdd;
    private String homeAdd;

    public Student(String firstName, String lastName, String birthDate, String bacId, String bacInfo, String univCycle, String emailAdd, String homeAdd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.bacId = bacId;
        this.bacInfo = bacInfo;
        this.univCycle = univCycle;
        this.emailAdd = emailAdd;
        this.homeAdd = homeAdd;
    }
    
    public Student( String firstName2, String lastName2, String birthDate2, int bacId2, String bacInfo2,
            String univCycle2, String emailAdd2, String homeAdd2) {
    }

   
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public void setBacId(String bacId) {
        this.bacId = bacId;
    }
    
    public void setBacInfo(String bacInfo) {
        this.bacInfo = bacInfo;
    }
    
    public void setUnivCycle(String univCycle) {
        this.univCycle = univCycle;
    }
    
    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }
    
    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }


    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getBirthDate() {
        return birthDate;
    }
    
    public String getBacId() {   
         return bacId;
    }
    
    public String getBacInfo() {
        return bacInfo;
    }
    
    public String getUnivCycle() {
        return univCycle;
    }
    
    public String getEmailAdd() {
        return emailAdd;
    }
    
    public String getHomeAdd() {
        return homeAdd;
            }   
}    
    

