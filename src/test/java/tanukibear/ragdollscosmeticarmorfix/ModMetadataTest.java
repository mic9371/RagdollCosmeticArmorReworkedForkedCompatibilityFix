package tanukibear.ragdollscosmeticarmorfix;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.VersionRange;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModMetadataTest {
    private static final Pattern DEPENDENCY_BLOCK = Pattern.compile(
            "\\[\\[dependencies\\.\"ragdollscosmeticarmorfix\"\\]\\]\\R(.*?)(?=\\R\\[\\[|\\z)",
            Pattern.DOTALL
    );
    private static final Pattern VERSION_RANGE = Pattern.compile("(?m)^versionRange = \"([^\"]+)\"$");
    private static final Pattern MOD_VERSION = Pattern.compile("(?m)^version = \"([^\"]+)\"$");

    @Test
    void cosmeticArmorRangeAcceptsThePublishedVersionAndKeepsTheChosenBoundaries() throws Exception {
        var range = VersionRange.createFromVersionSpec(dependencyVersionRange("cosmeticarmorreworkedforked"));

        assertTrue(range.containsVersion(new DefaultArtifactVersion("1.21.1-0.0.4")));
        assertFalse(range.containsVersion(new DefaultArtifactVersion("1.21.1-0.0.3")));
        assertFalse(range.containsVersion(new DefaultArtifactVersion("1.21.1-0.1")));
    }

    @Test
    void generatedMetadataUsesReleaseVersionOne() throws Exception {
        var matcher = MOD_VERSION.matcher(metadata());

        assertTrue(matcher.find(), "mod version is missing");
        assertEquals("1.0", matcher.group(1));
    }

    private static String dependencyVersionRange(String modId) throws Exception {
        var blocks = DEPENDENCY_BLOCK.matcher(metadata());
        while (blocks.find()) {
            var block = blocks.group(1);
            if (block.contains("modId = \"" + modId + "\"")) {
                var range = VERSION_RANGE.matcher(block);
                assertTrue(range.find(), "version range is missing for " + modId);
                return range.group(1);
            }
        }
        throw new AssertionError("dependency is missing: " + modId);
    }

    private static String metadata() throws Exception {
        return Files.readString(Path.of(System.getProperty("user.dir"))
                .resolve("build/generated/sources/modMetadata/META-INF/neoforge.mods.toml"));
    }
}
