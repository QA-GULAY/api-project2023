package master_branch.http_request.pojos.regres.pojo.pojos.regres.pojo;

public class Master_BranchPojo {

    private String personId;
    private String name;

    private String age;
    private String email;
    private Address address;
    public Master_BranchPojo(){

    }
    public Master_BranchPojo(String personId, String name, String age, String email, Address address) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "Master_branchPojo{" +
                "personId='" + personId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", address=" + address +
                '}';
    }
}
