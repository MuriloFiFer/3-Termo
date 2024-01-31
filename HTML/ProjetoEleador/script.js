const elevators = document.querySelectorAll('.elevator');
const floors = document.querySelectorAll('.floor');

const moveElevator = (elevator, destination) => {
    const floorHeight = 60;
    const destinationPosition = (destination === 'T' ? 0 : 6 - destination) * floorHeight;

    elevator.style.top = `${destinationPosition}px`;
    elevator.dataset.floor = destination;
};

elevators.forEach(elevator => {
    elevator.addEventListener('click', () => {
        const currentFloor = elevator.dataset.floor;
        alert(`Elevador ${elevator.textContent} está no ${currentFloor}º andar.`);
    });
});

floors.forEach(floor => {
    floor.addEventListener('click', () => {
        const destinationFloor = floor.dataset.floor;
        const elevator1 = document.getElementById('elevator1');
        const elevator2 = document.getElementById('elevator2');

        const currentFloor1 = elevator1.dataset.floor;
        const currentFloor2 = elevator2.dataset.floor;

        if (Math.abs(currentFloor1 - destinationFloor) <= Math.abs(currentFloor2 - destinationFloor) || currentFloor1 === currentFloor2) {
            moveElevator(elevator1, destinationFloor);
            alert(`Movendo elevador 1 para o ${destinationFloor}º andar.`);
        } else {
            moveElevator(elevator2, destinationFloor);
            alert(`Movendo elevador 2 para o ${destinationFloor}º andar.`);
        }
    });
});
