package io.quarkus.dev;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Object that is used to pass context data from the plugin doing the invocation
 * into the dev mode process using java serialization.
 *
 * There is no need to worry about compat as both sides will always be using the same version
 */
public class DevModeContext implements Serializable {

    private final List<URL> classPath = new ArrayList<>();
    private final List<ModuleInfo> modules = new ArrayList<>();
    private final Map<String, String> systemProperties = new HashMap<>();
    private final Map<String, String> buildSystemProperties = new HashMap<>();
    private String sourceEncoding;

    private final List<File> classesRoots = new ArrayList<>();
    private File frameworkClassesDir;
    private File cacheDir;
    private boolean test;
    private boolean abortOnFailedStart;

    private List<String> compilerOptions;
    private String sourceJavaVersion;
    private String targetJvmVersion;

    public List<URL> getClassPath() {
        return classPath;
    }

    public List<ModuleInfo> getModules() {
        return modules;
    }

    public Map<String, String> getSystemProperties() {
        return systemProperties;
    }

    public Map<String, String> getBuildSystemProperties() {
        return buildSystemProperties;
    }

    public String getSourceEncoding() {
        return sourceEncoding;
    }

    public void setSourceEncoding(String sourceEncoding) {
        this.sourceEncoding = sourceEncoding;
    }

    public List<File> getClassesRoots() {
        return classesRoots;
    }

    public File getFrameworkClassesDir() {
        return frameworkClassesDir;
    }

    public void setFrameworkClassesDir(File frameworkClassesDir) {
        this.frameworkClassesDir = frameworkClassesDir;
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public void setCacheDir(File cacheDir) {
        this.cacheDir = cacheDir;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean isAbortOnFailedStart() {
        return abortOnFailedStart;
    }

    public void setAbortOnFailedStart(boolean abortOnFailedStart) {
        this.abortOnFailedStart = abortOnFailedStart;
    }

    public List<String> getCompilerOptions() {
        return compilerOptions;
    }

    public void setCompilerOptions(List<String> compilerOptions) {
        this.compilerOptions = compilerOptions;
    }

    public String getSourceJavaVersion() {
        return sourceJavaVersion;
    }

    public void setSourceJavaVersion(String sourceJavaVersion) {
        this.sourceJavaVersion = sourceJavaVersion;
    }

    public String getTargetJvmVersion() {
        return targetJvmVersion;
    }

    public void setTargetJvmVersion(String targetJvmVersion) {
        this.targetJvmVersion = targetJvmVersion;
    }

    public static class ModuleInfo implements Serializable {

        private final String name;
        private final String projectDirectory;
        private final Set<String> sourcePaths;
        private final String classesPath;
        private final String resourcePath;

        public ModuleInfo(
                String name,
                String projectDirectory,
                Set<String> sourcePaths,
                String classesPath,
                String resourcePath) {
            this.name = name;
            this.projectDirectory = projectDirectory;
            this.sourcePaths = sourcePaths;
            this.classesPath = classesPath;
            this.resourcePath = resourcePath;
        }

        public String getName() {
            return name;
        }

        public String getProjectDirectory() {
            return projectDirectory;
        }

        public Set<String> getSourcePaths() {
            return sourcePaths;
        }

        public void addSourcePaths(Collection<String> additionalPaths) {
            additionalPaths.stream().map(p -> projectDirectory + File.separator + p).forEach(sourcePaths::add);
        }

        public String getClassesPath() {
            return classesPath;
        }

        public String getResourcePath() {
            return resourcePath;
        }
    }

}
