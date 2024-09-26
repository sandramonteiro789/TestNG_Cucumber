package utils;

import java.io.IOException;

import org.testng.annotations.Test;

public class DockerGridClass {

	@Test
	void startDockerGrid() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
		Thread.sleep(15000);
	}
	
	@Test
	void stopDockerGrid() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
		Thread.sleep(15000);
		
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}
}