package uk.co.mindbadger.footballresults.season;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class SeasonCacheTest {
	
	private SeasonCache objectUnderTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldInitialiseWithNoRowsIfPreviousTableIsNull () {
		// Given
		
		// When
		
		// Then
	}
}
