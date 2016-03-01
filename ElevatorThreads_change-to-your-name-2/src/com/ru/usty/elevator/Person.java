package com.ru.usty.elevator;

public class Person implements Runnable{
	int sourceFloor, destinationFloor;
	public Person(int sourceFloor, int destinationFloor){
		this.sourceFloor = sourceFloor;
		this.destinationFloor = destinationFloor;
	}
	@Override
	public void run() {
		try {
			
			
			ElevatorScene.elevatorWaitMutex.acquire();
			ElevatorScene.personInElevatorCountMutex.acquire();
			ElevatorScene.semaphore1.acquire(); //wait
			ElevatorScene.elevatorWaitMutex.release();
			System.out.println("Fer inni lyftu");
			ElevatorScene.scene.incrementNumberOfPeopleInElevator(1);
			ElevatorScene.personInElevatorCountMutex.release();
			ElevatorScene.scene.decrementNumberOfPeopleWaitingAtFloor(sourceFloor);
			System.out.println("kominn inní lyftu");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		
		} 
		
		try {
			ElevatorScene.elevatorGoOutMutex.acquire();
			ElevatorScene.scene.decrementNumberOfPeopleInElevator(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Person Thread released");
	}

}
