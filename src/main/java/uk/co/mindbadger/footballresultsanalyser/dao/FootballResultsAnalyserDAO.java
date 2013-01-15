package uk.co.mindbadger.footballresultsanalyser.dao;

import java.util.List;

import uk.co.mindbadger.footballresultsanalyser.domain.Season;

public interface FootballResultsAnalyserDAO {
    public List<Season> getSeasons ();
}
