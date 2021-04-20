package vo;

public class Vacancy {
    private String position;
    private String companyName;
    private String city;
    private String salary;
    private String siteName;
    private String url;

    public Vacancy(String position, String companyName, String city, String salary, String siteName, String url) {
        this.position = position;
        this.companyName = companyName;
        this.city = city;
        this.salary = salary.equals("")? "не указана" : salary;
        this.siteName = siteName;
        this.url = url;
    }

    public String getPosition() {
        return position;
    }

    public String getSalary() {
        return salary;
    }

    public String getCity() {
        return city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;

        Vacancy vacancy = (Vacancy) o;

        if (getPosition() != null ? !getPosition().equals(vacancy.getPosition()) : vacancy.getPosition() != null) return false;
        if (getSalary() != null ? !getSalary().equals(vacancy.getSalary()) : vacancy.getSalary() != null) return false;
        if (getCity() != null ? !getCity().equals(vacancy.getCity()) : vacancy.getCity() != null) return false;
        if (getCompanyName() != null ? !getCompanyName().equals(vacancy.getCompanyName()) : vacancy.getCompanyName() != null)
            return false;
        if (getSiteName() != null ? !getSiteName().equals(vacancy.getSiteName()) : vacancy.getSiteName() != null)
            return false;
        return getUrl() != null ? getUrl().equals(vacancy.getUrl()) : vacancy.getUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getPosition() != null ? getPosition().hashCode() : 0;
        result = 31 * result + (getSalary() != null ? getSalary().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getCompanyName() != null ? getCompanyName().hashCode() : 0);
        result = 31 * result + (getSiteName() != null ? getSiteName().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Вакансия(%s): %s в компании \"%s\" зарплата - %s",
                city, position, companyName, salary);
    }
}
