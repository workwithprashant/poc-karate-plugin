package simulations

import com.intuit.karate.gatling.KarateProtocol
import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class DemoLoadSimulation extends Simulation {
  val usersRamp = 2;
  val runDuration = 5;
  val rampDuration = 10;
  val protocol: KarateProtocol = karateProtocol("/*" -> Nil)
  val getDemoSimulation: ScenarioBuilder = scenario("Demo Simulation").during(runDuration) {
    exec(karateFeature("classpath:examples/users/users.feature@demo"))
  }

  setUp(
    getDemoSimulation.inject(rampUsers(usersRamp).during(rampDuration)).protocols(protocol)
  );
}
