package com.ru.usty.elevator;

public class Person implements Runnable{
	int sourceFloor, destinationFloor;
	public Person(int sourceFloor, int destinationFloor){
		this.sourceFloor = sourceFloor;
		this.destinationFloor = destinationFloor;
	}
	@Override
	public void run() {
		System.out.println("Persona til " + sourceFloor + destinationFloor);
						
					ElevatorScene.scene.getSemaphore(sourceFloor);
						System.out.println("Kominn inn");
						
					ElevatorScene.scene.incrementNumberOfPeopleInElevator(1);
					ElevatorScene.scene.incrementNumberOfPeopleGoingOutFloor(destinationFloor);
					ElevatorScene.scene.decrementNumberOfPeopleWaitingAtFloor(sourceFloor);
						
	
						ElevatorScene.scene.getOutSemaphore(destinationFloor);
						ElevatorScene.scene.decrementNumberOfPeopleInElevator(1);
						ElevatorScene.scene.decrementNumberOfPeopleGoingOutFloor(destinationFloor);
					
					
					System.out.println("		farinn út");
					
	
					
}
}
