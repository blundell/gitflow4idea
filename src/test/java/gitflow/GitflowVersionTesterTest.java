package gitflow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.VcsException;
import git4idea.commands.Git;
import git4idea.commands.GitCommandResult;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GitflowVersionTesterTest {

    @Test
    public void isSupportedVersion() throws VcsException {
        Gitflow mockGitflow = mock(Gitflow.class);
        Project mockProject = mock(Project.class);
        GitflowVersionTester tester = new GitflowVersionTester(mockGitflow, mockProject);

        GitCommandResult mockGitCommandResult = mock(GitCommandResult.class);
        when(mockGitflow.version(mockProject)).thenReturn(mockGitCommandResult);
        when(mockGitCommandResult.getOutputOrThrow()).thenReturn("1.11.0 (AVH Edition)");

        assertTrue(tester.isSupportedVersion());
    }

    @Test
    public void isUnsupportedVersion() throws VcsException {
        Gitflow mockGitflow = mock(Gitflow.class);
        Project mockProject = mock(Project.class);
        GitflowVersionTester tester = new GitflowVersionTester(mockGitflow, mockProject);

        GitCommandResult mockGitCommandResult = mock(GitCommandResult.class);
        when(mockGitflow.version(mockProject)).thenReturn(mockGitCommandResult);
        when(mockGitCommandResult.getOutputOrThrow()).thenReturn("0.4.0");

        assertFalse(tester.isSupportedVersion());
    }
}