package assignment07;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PathFinderTest {
	
	PathFinder straight = new PathFinder("straight.txt", "straightSolJeremy.txt");
	PathFinder bigMaze = new PathFinder("bigMaze.txt", "bigMazeSolJeremy.txt");
	PathFinder classic = new PathFinder("classic.txt", "classicSolJeremy.txt");
	PathFinder demoMaze = new PathFinder("demoMaze.txt", "demoMazeSolJeremy.txt");
	PathFinder medium = new PathFinder("mediumMaze.txt", "mediumSolJeremy.txt");
	PathFinder random = new PathFinder("randomMaze.txt", "randomSolJeremy.txt");
	PathFinder tinyMaze = new PathFinder("tinyMaze.txt", "tinyMazeSolJeremy.txt");
	PathFinder tinyOpen = new PathFinder("tinyOpen.txt", "tinyOpenSolJeremy.txt");
	PathFinder turn = new PathFinder("turn.txt", "turnSolJeremy.txt");
	PathFinder unsolvable = new PathFinder("unsolvable.txt", "unsolvableSolJeremy.txt");
	PathFinder badInput = new PathFinder("badInput.txt", "badInputSol.txt");
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSize() throws IOException {
		Maze testMaze = new Maze("straight.txt", "straightSolJeremy.txt");
		Maze testMaze2 = new Maze("bigMaze.txt", "bigMazeSolJeremy.txt");
		int size = testMaze.mazeGraph.size();
		int size2 = testMaze2.mazeGraph.size();
		Assert.assertEquals(testMaze.mazeGraph.getWidth() * testMaze.mazeGraph.getHeight(), size);
		Assert.assertEquals(testMaze2.mazeGraph.getWidth() * testMaze2.mazeGraph.getHeight(), size2);
	}

}
