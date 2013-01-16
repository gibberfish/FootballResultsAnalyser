package uk.co.mindbadger.footballresultsanalyser.dao;

import java.util.List;
import java.util.Set;

import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;

public interface FootballResultsAnalyserDAO {
    public List<Season> getSeasons ();
    
    public Set<SeasonDivision> getDivisionsForSeason (int seasonNumber);
}
