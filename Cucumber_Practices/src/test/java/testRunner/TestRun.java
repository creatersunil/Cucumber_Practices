package testRunner;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
		(
				features=".//Features/",
				glue="stepDefinition",
				dryRun=false,
				plugin = { "pretty","html:target/cucumber-reports.html"
		                  },
				monochrome = true
				//tags= "@sanity"
				//plugin= {"pretty","html:test-output"}
		)
public class TestRun {

}
