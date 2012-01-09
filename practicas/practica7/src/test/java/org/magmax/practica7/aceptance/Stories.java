/*
 * Copyright (C) 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package org.magmax.practica7.aceptance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Stories extends JUnitStories {

    @Override
    protected List<String> storyPaths() {
        String initial_subdirectory = "src/test/resources";
        List<String> story_patterns = Arrays.asList("**/*.story");
        StoryFinder storyfinder = new StoryFinder();
        return storyfinder.findPaths(initial_subdirectory, story_patterns, new ArrayList<String>());
    }

    @Override
    public Configuration configuration() {
        Configuration result = super.configuration();
        result.useStoryReporterBuilder(getReporter());
        return result;
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        ArrayList<Object> steps = new ArrayList<Object>();
        steps.add(new PersonAgregationSteps());
        steps.add(new PersonSearchSteps());
        InstanceStepsFactory stepsFactory = new InstanceStepsFactory(configuration(), steps);
        return stepsFactory.createCandidateSteps();
    }

    private StoryReporterBuilder getReporter() {
        StoryReporterBuilder result = new StoryReporterBuilder();
        result.withFailureTrace(true);
        result.withFormats(Format.CONSOLE, Format.HTML, Format.STATS);
        result.withFailureTraceCompression(false);
        return result;
    }
}
