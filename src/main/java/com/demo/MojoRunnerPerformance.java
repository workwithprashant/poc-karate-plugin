package com.demo;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.twdata.maven.mojoexecutor.MojoExecutor.*;

/**
 * Execute Runner Performance Test.
 *
 * @author workwithprashant@gmail.com
 */
@Mojo(name = "performancetest",
        defaultPhase = LifecyclePhase.PROCESS_SOURCES,
        requiresProject = false,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        threadSafe = true,
        requiresOnline = true)
public class MojoRunnerPerformance
        extends AbstractMojo {

    /* Initialize the logger */
    private static final Logger log = LoggerFactory.getLogger(MojoRunnerPerformance.class);

    /**
     * Autogenerated by Maven using POM.xml
     */
    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject mavenProject;

    /**
     * Autogenerated by Maven using POM.xml
     */
    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession mavenSession;

    /**
     * Autogenerated by Maven using POM.xml
     */
    @Component
    private BuildPluginManager pluginManager;

    /**
     * Perform whatever build-process behavior this Mojo implements.
     * This is the main trigger for the Mojo inside the Maven system, and
     * allows the Mojo to communicate fatal errors by throwing an instance of MojoExecutionException.
     *
     * @throws MojoExecutionException Mojo Execution Exception
     */
    @Override
    public void execute()
            throws MojoExecutionException {
        log.trace("Starting MojoRunnerPerformance execute");
        try {
            log.trace("Starting MojoRunnerPerformance try block");

            MojoHelper.unpackTestArtifacts(mavenProject, mavenSession, pluginManager);

            /* Executes build plugins similar way as it does through pom.xml
             * Following plugin is used to execute performance tests
             */
            Element[] pluginConfig = getGatlingPluginConfigurationParameters().toArray(new Element[0]);
            executeMojo(
                    plugin(
                            groupId("io.gatling"),
                            artifactId("gatling-maven-plugin"),
                            version("3.1.1")
                    ),
                    goal("test"),
                    configuration(pluginConfig),
                    executionEnvironment(
                            mavenProject,
                            mavenSession,
                            pluginManager
                    )
            );
        } catch (Exception e) {
            throw new MojoExecutionException("Error running tests due to " + ExceptionUtils.getStackTrace(e));

        }
    }

    public List<Element> getGatlingPluginConfigurationParameters() {
        ArrayList<Element> configuration = new ArrayList<>();
        configuration.add(new Element("runMultipleSimulations", "true"));
        configuration.add(new Element("simulationsFolder", "${project.build.testOutputDirectory}"));
        configuration.add(new Element("disableCompiler", "true"));
        configuration.add(new Element("includes",
                element("include", String.format("simulations.%s", "DemoLoadSimulation"))
        ));

        return configuration;
    }

}