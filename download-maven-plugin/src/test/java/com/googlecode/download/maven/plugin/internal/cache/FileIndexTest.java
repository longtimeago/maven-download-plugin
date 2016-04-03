package com.googlecode.download.maven.plugin.internal.cache;

import java.io.File;
import java.io.IOException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Tests for {@code FileIndex}.
 * @author Paul Polishchuk
 * @since 1.3.1
 */
public final class FileIndexTest {

    @Rule
    public final TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void putCheckAndGet() throws IOException {
        final Index index = new FileIndex(this.tmp.newFolder("cacheDir"));
        final String url = "/first/url";
        final String path = "some path";
        index.put(url, path);
        MatcherAssert.assertThat(index.contains(url), Matchers.is(true));
        MatcherAssert.assertThat(index.get(url), Matchers.is(path));
    }

    @Test
    public void checkForNotExistent() throws IOException {
        final Index index = new FileIndex(this.tmp.newFolder("cacheDir"));
        MatcherAssert.assertThat(
            index.contains("/not/exist"), Matchers.is(false)
        );
    }

    @Test(expected = IllegalStateException.class)
    public void throwsIfGetNotExistent() throws IOException {
        new FileIndex(this.tmp.newFolder("cacheDir")).get("/not/exist");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfBaseNotExist() {
        new FileIndex(new File("notExist"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfBaseNotADir() throws IOException {
        new FileIndex(this.tmp.newFile("notADir"));
    }
}