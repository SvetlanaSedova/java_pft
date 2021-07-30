package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("SvetlanaSedova", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(commit);
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
