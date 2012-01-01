/*
 * Copyright (C) 2011,2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.magmax.master.practica6.aceptance;

import java.util.Arrays;
import java.util.List;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class RetrieveTemperatureStories extends JUnitStories {

    @Override
    public Configuration configuration() {
        return super.configuration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                    .withDefaultFormats()
                    .withFormats(Format.CONSOLE, Format.HTML, Format.STATS));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new RetrieveTemperatureSteps()).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        List<String> result = new StoryFinder().findPaths("src/test/resources", Arrays.asList("**/temperature_scenario.story"), null);
        return result;
    }
}
