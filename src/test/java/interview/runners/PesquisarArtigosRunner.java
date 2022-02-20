
package interview.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/interview/features",
		plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber.json", "junit:target/cucumber.xml" },
		monochrome = true,
		tags = {"@PesquisarArtigos"},
		glue = {"interview.stepDefinition"},
		dryRun = false,
		strict = true )
public class PesquisarArtigosRunner {
	
}
 