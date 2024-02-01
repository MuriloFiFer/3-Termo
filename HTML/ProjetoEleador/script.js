// Seleciona todos os elementos com a classe 'elevator'
const elevators = document.querySelectorAll('.elevator');
// Seleciona todos os elementos com a classe 'floor'
const floors = document.querySelectorAll('.floor');

// Função para movimentar o elevador para um determinado andar
const moveElevator = (elevator, destination) => {
    const floorHeight = 60;

    // Calcula a posição de destino correta
    const destinationPosition = (destination === 'T' ? 0 : 6 - destination) * floorHeight;

    // Move o elevador para a posição de destino
    elevator.style.top = `${destinationPosition}px`;
    // Atualiza o andar atual do elevador
    elevator.dataset.floor = destination;
};

// Adiciona um ouvinte de evento de clique para cada elevador
elevators.forEach(elevator => {
    elevator.addEventListener('click', () => {
        const currentFloor = elevator.dataset.floor;
        alert(`Elevador ${elevator.textContent} está no ${currentFloor}º andar.`);
    });
});

// Adiciona um ouvinte de evento de clique para cada andar
floors.forEach(floor => {
    floor.addEventListener('click', () => {
        const destinationFloor = floor.dataset.floor;
        const elevator1 = document.getElementById('elevator1');
        const elevator2 = document.getElementById('elevator2');

        // Verifica se os elevadores existem antes de prosseguir
        if (elevator1 && elevator2) {
            const currentFloor1 = elevator1.dataset.floor;
            const currentFloor2 = elevator2.dataset.floor;

            // Verifica qual elevador está mais próximo do destino
            if (Math.abs(currentFloor1 - destinationFloor) <= Math.abs(currentFloor2 - destinationFloor) || currentFloor1 === currentFloor2) {
                // Move o Elevador 1 para o destino
                moveElevator(elevator1, destinationFloor);
                alert(`Movendo elevador 1 para o ${destinationFloor}º andar.`);
            } else {
                // Move o Elevador 2 para o destino
                moveElevator(elevator2, destinationFloor);
                alert(`Movendo elevador 2 para o ${destinationFloor}º andar.`);
            }
        } else {
            console.error('Os elevadores não foram encontrados.');
        }
    });
});
