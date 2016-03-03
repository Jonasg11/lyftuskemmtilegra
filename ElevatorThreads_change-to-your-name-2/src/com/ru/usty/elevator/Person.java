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
						ElevatorScene.scene.getWaitSemaphore(sourceFloor); //her tokum vid mutex til ad vera einir ad bidja um naestu semaphoru
					ElevatorScene.scene.getSemaphore(sourceFloor); // herna naum vid i semaphoru sem ad gefur leyfi til ad fara inni lyfuna
						ElevatorScene.scene.setWaitSemaphore(sourceFloor); // skilum mutexnum svo ad naessti geti reynt ad komast inn
						System.out.println("Kominn inn");
						
						//tessi segja sig frekar sjalf
					ElevatorScene.scene.incrementNumberOfPeopleInElevator(1);
					ElevatorScene.scene.incrementNumberOfPeopleGoingOutFloor(destinationFloor);
					ElevatorScene.scene.decrementNumberOfPeopleWaitingAtFloor(sourceFloor);
						
	
						ElevatorScene.scene.getOutSemaphore(destinationFloor);//her naum vid i semaphoru sem leyfir okkur ad fara ut
						//tessi segja sig sjalf
						ElevatorScene.scene.decrementNumberOfPeopleInElevator(1);
						ElevatorScene.scene.decrementNumberOfPeopleGoingOutFloor(destinationFloor);
					
					
					System.out.println("		farinn út");
					
	
					
}
}
