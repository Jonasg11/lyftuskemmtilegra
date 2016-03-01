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
				ElevatorScene.semaphore1.acquire(); //wait
					ElevatorScene.personInElevatorCountMutex.acquire();
			ElevatorScene.elevatorWaitMutex.release();
						ElevatorScene.scene.incrementNumberOfPeopleInElevator(1);
						ElevatorScene.scene.incrementNumberOfPeopleGoingOutFloor(destinationFloor);
						ElevatorScene.scene.decrementNumberOfPeopleWaitingAtFloor(sourceFloor);
					ElevatorScene.personInElevatorCountMutex.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		
		} 
		
		try {
			ElevatorScene.elevatorGoOut.acquire();
			ElevatorScene.personInElevatorCountMutex.acquire();
				ElevatorScene.scene.decrementNumberOfPeopleInElevator(1);
				ElevatorScene.scene.decrementNumberOfPeopleGoingOutFloor();
			ElevatorScene.personInElevatorCountMutex.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Person Thread released");
	}

}
