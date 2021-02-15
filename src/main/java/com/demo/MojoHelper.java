package com.demo;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import static org.twdata.maven.mojoexecutor.MojoExecutor.*;

/**
 * @author workwithprashant@gmail.com
 */
public class MojoHelper {

    public static void unpackTestArtifacts(MavenProject mavenProject, MavenSession mavenSession, BuildPluginManager pluginManager) throws MojoExecutionException {
        executeMojo(
                plugin(
                        groupId("org.apache.maven.plugins"),
                        artifactId("maven-dependency-plugin"),
                        version("3.1.1")
                ),
                goal("unpack"),
                configuration(
                        element("artifactItems", element("artifactItem",
                                element("groupId", "com.demo"),
                                element("artifactId", "poc-karate-plugin"),
                                element("version", "2.0.0"),
                                element("outputDirectory", "${project.build.testOutputDirectory}")
                                )
                        )
                ),
                executionEnvironment(
                        mavenProject,
                        mavenSession,
                        pluginManager
                )
        );
    }
}
