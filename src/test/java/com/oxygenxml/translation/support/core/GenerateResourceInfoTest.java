package com.oxygenxml.translation.support.core;

import com.oxygenxml.translation.exceptions.StoppedByUserException;
import com.oxygenxml.translation.support.storage.ResourceInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

/**
 * Tests for generating the milestone required information based on the file system. 
 */
public class GenerateResourceInfoTest extends TranslationPackageTestBase {
  
  /**
   * Iterates over a directory structure and generates MD5's and relative paths.
   */
  public void testGenerateRelativePaths() throws NoSuchAlgorithmException, IOException, StoppedByUserException {
    File dirPath = TestUtil.getPath("testIteration-ResourceInfoTest");
    List<ResourceInfo> list = generatesModifiedFilesUsingFileSystemBuilder(dirPath);

    Assert.assertEquals(
        "dir1/md5.txt      1308e502a17d62d0585d1487228b204c\n" + 
        "dir1/md5_no2.txt  521304ca436443d97ccf68ee919c03b3\n" + 
        "dir2/md5.txt      55047487acf9f525244b12cff4bfc49c\n" + 
        "dir2/md5_no2.txt  5c24a78aec732e9626a4a7114efd98b1\n" + 
        "", 
        TestUtil.dump(list));
  }

  /**
   * Iterates over a directory structure and generates MD5's and relative paths.
   */
  public void testGenerateRelativePaths_2() throws NoSuchAlgorithmException, FileNotFoundException, IOException, StoppedByUserException {
    File dirPath = TestUtil.getPath("testGenerate-ResourceInfoTest");

    List<ResourceInfo> list = generatesModifiedFilesUsingFileSystemBuilder(dirPath);

    String expectedResult = 
        "md5.txt      357f82eaa10b09268cc26c766fc03c16\n" + 
        "md5_no2.txt  521304ca436443d97ccf68ee919c03b3\n";

    Assert.assertEquals(expectedResult, TestUtil.dump(list));
  }

  /**
   * Collects milestone information from a directory structure.
   * 
   * @throws Exception If it fails.
   */
  public void testGenerateResourceInfo() throws Exception {
    File rootDir = TestUtil.getPath("generateMilestone-Test");
    List<ResourceInfo> milestoneInfo = generatesModifiedFilesUsingFileSystemBuilder(rootDir);

    ArrayList<ResourceInfo> expectedResult = new ArrayList<ResourceInfo>();
    expectedResult.add(new ResourceInfo("754d9436d3a245ad9a340b8d9929fc46", "testGenerate/md5.txt"));
    expectedResult.add(new ResourceInfo("3c01bd69152843f5aada9595c6b75bf2", "testGenerate/md5_no2.txt"));
    expectedResult.add(new ResourceInfo("754d9436d3a245ad9a340b8d9929fc46", "testIteration/dir1/md5.txt"));
    expectedResult.add(new ResourceInfo("521304ca436443d97ccf68ee919c03b3", "testIteration/dir1/md5_no2.txt"));
    expectedResult.add(new ResourceInfo("55047487acf9f525244b12cff4bfc49c", "testIteration/dir2/md5.txt"));
    expectedResult.add(new ResourceInfo("5c24a78aec732e9626a4a7114efd98b1", "testIteration/dir2/md5_no2.txt"));

    Assert.assertEquals(TestUtil.dump(expectedResult), TestUtil.dump(milestoneInfo));
  }
}
