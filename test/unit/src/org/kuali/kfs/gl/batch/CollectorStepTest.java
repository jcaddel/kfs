/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.module.gl.batch;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.context.KualiTestBase;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.gl.batch.collector.CollectorStep;
import org.kuali.test.RequiresSpringContext;
import org.kuali.test.suite.RelatesTo;

/**
 * Tests the CollecterStep.
 * DEPENDENCIES:
 * 
 * Collector card xml file transaction1.xml must be in /opt/kuali/dev/staging/collector/
 * this file can be obtained by running the project's ant dist-local, or copying from
 * build/externalConfigDirectory/static/staging/collector/
 */
@RequiresSpringContext
public class CollectorStepTest extends KualiTestBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CollectorStepTest.class);
    
    public CollectorStepTest() {
        super();
    }

    /**
     * Creats .done file for test input file.
     *      
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        File directory = new File("/opt/kuali/unt/staging/");
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                System.err.println("TESTING: " + file.getName());
                if (file.isDirectory()) {
                    File[] files2 = file.listFiles();
                    for (File file2 : files2) {
                        System.err.println("TESTING2: " + file2.getName());
                    }
                }
            }
        }
        
        String doneFileName = SpringServiceLocator.getCollectorInputFileType().getDirectoryPath() + "/gl_collector1.done";
        File doneFile = new File(doneFileName);
        if (!doneFile.exists()) {
            doneFile.createNewFile();
        }
    }
    /**
     * Tests the whole step completes successfully.
     */
    @RelatesTo(RelatesTo.JiraIssue.KULUT29)
    public void testAll() throws Exception {
        CollectorStep collectorStep = SpringServiceLocator.getCollectorStep();
        boolean goodExit = collectorStep.execute(getClass().getName());
        
        assertTrue("collector step did not exit with pass", goodExit);
    }
    
}
