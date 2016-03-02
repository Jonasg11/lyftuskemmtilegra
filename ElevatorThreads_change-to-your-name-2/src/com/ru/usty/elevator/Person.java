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
			while(ElevatorScene.scene.getCurrentFloorForElevator(1) != sourceFloor){}
			ElevatorScene.elevatorWaitMutex.acquire();
			while(ElevatorScene.waitToGoIn) {}
			ElevatorScene.elevatorWaitMutex.release();
				ElevatorScene.semaphore1.acquire(); //wait	
				System.out.println("				Kominn inní lyftu ");
				ElevatorScene.personInElevatorCountMutex.acquire();
			
					
					
			
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
			System.out.println("				fer ut ur lyftu ");
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
