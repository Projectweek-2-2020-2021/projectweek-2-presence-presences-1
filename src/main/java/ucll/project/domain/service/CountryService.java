package ucll.project.domain.service;

import ucll.project.domain.db.CountryDB;
import ucll.project.domain.db.CountryDBSQL;
import ucll.project.domain.model.Country;

import java.sql.Connection;
import java.util.List;

public class CountryService {
    private CountryDB db = new CountryDBSQL();

    public void addCountry(Country country) {
        db.add(country);
    }

    public List<Country> getCountries() {
        return db.getAll();
    }

    /**
     * @return connection with database
     */
    public Connection getConnection() {
        return db.getConnection();
    }

    /**
     * Reconnect DBSQL to database
     */
    public void reConnect() {
        db.reConnect();
    }

    public Country getMostPopularCountry() {
        int highestVotes = -1;
        Country mostPopular = null;
        List<Country> countryList = getCountries();
        for (int i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).getVotes() > highestVotes) {
                mostPopular = countryList.get(i);
                highestVotes = mostPopular.getVotes();
            }
        }
        return mostPopular;
    }
}
