package uo.ri;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	uo.ri.associations.AllTests.class,
	uo.ri.domain.AllTests.class,
})
public class AllTests { }
