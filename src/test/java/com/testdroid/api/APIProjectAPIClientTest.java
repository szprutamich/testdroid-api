package com.testdroid.api;

import com.testdroid.api.model.APIProject;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIProjectAPIClientTest extends APIClientTest {

    @ParameterizedTest
    @ArgumentsSource(APIClientTest.APIClientProvider.class)
    void createProjectTest(AbstractAPIClient apiKeyClient) throws APIException {
        String projectName = generateUnique("testProject");
        APIProject project = apiKeyClient.me().createProject(projectName);
        assertThat(project.getName(), is(projectName));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientTest.APIClientProvider.class)
    void updateProjectTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIProject project = apiKeyClient.me().createProject(generateUnique("testProject"));
        String updatedProjectName = generateUnique("testProject");
        project.setName(updatedProjectName);
        project.setDescription("Description of testProject");
        project.update();
        assertThat(project.getName(), is(updatedProjectName));
        assertThat(project.getDescription(), is("Description of testProject"));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientTest.APIClientProvider.class)
    void deleteProjectTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIProject project = apiKeyClient.me().createProject(generateUnique("testProject"));
        project.delete();
    }
}
