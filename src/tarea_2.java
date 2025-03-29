/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class tarea_2 { //clase principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);// crear el objeto para leer la entrada
        System.out.println("ingrese la cantidad de elementos: ");
        int n = scanner.nextInt();// leer un numero entero
        int[] array = new int[n]; // leer arreglo de "n" tamaño
        System.out.println("ingrese los elementos:");
        for (int i = 0; i < n; i++) { // bucle
            array[i] = scanner.nextInt();// lee cada elemento 
        }
        
        System.out.println("seleccione el metodo de ordenamiento:"); // seleccionamos el metodo de ordenamiento
        System.out.println("1. selection sort");
        System.out.println("2. bubble sort");
        System.out.println("3. insertion sort");
        System.out.println("4. merge sort");
        System.out.println("5. quick sort");
        System.out.println("6. heap sort");
        System.out.println("7. counting sort");
        System.out.println("8. Radix sort");
        System.out.println("9. Bucket sort");
        
        int choice = scanner.nextInt();// leemos la opcion elegida 
        boolean valid = true; // validamos opción 
        switch (choice) {
            case 1: // se ejecuta la seleccion
                selectionSort(array);
                break; // sale switch lo mismo realiza con las demas opciones  
            case 2:
                bubbleSort(array);
                break;
            case 3:
                insertionSort(array);
                break;
            case 4:
                mergeSort(array, 0, array.length - 1);
                break;
            case 5:
                quickSort(array, 0, array.length - 1);
                break;
            case 6:
                heapSort(array);
                break;
            case 7:
                array = countingSort(array);
                break;
            case 8:
                radixSort(array);
                break;
            case 9:
                array = bucketSort(array);
                break;
            default: // opcion valida
                System.out.println("Opcion invalida");
                valid = false; // opcion invalida
                break;
        }
        
        if (valid) { // si al opcion es calida se realiza lo siguiente
            System.out.println("arreglo ordenado: " + Arrays.toString(array));
        }
    }

    // Selection Sort
    private static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // el minimo es la posicion "i"
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) { // busca el minimo real
                    minIndex = j;
                }
            }
            int temp = array[minIndex]; // intercambia el minimo con la posicion "i"
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    // Bubble Sort
    private static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) { // compara elementos y los ordena
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort
    private static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Merge Sort
    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) { // condicion para la recursión
            int mid = (left + right) / 2;
            mergeSort(array, left, mid); // Ordena la mitad izquierda
            mergeSort(array, mid + 1, right); //Ordena la mitad derecha
            merge(array, left, mid, right); //Combina ambas mitades
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < n1) {
            array[k++] = leftArray[i++];
        }
        while (j < n2) {
            array[k++] = rightArray[j++];
        }
    }

    // Quick Sort
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high); // indice de particion
            quickSort(array, low, pi - 1); //Ordena la parte izquierda
            quickSort(array, pi + 1, high); //Ordena la parte derecha
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Heap Sort
    private static void heapSort(int[] array) {
        int n = array.length; // construye un monto maximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        // extrae elementos uno a uno
        for (int i = n - 1; i > 0; i--) { // mueve la raiz al final
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0); // recostruye
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest);
        }
    }

    // Counting Sort
    private static int[] countingSort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        int min = Arrays.stream(array).min().getAsInt();
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[array.length]; // arreglo para contar 
        // llena el "count" y construye el arreglo ordenado
        for (int num : array) {
            count[num - min]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }
        return output;
    }

    // Radix Sort
    private static void radixSort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) { // para cada digito
            countingSortForRadix(array, exp); // ordena el usando couting scort
        }
    }

    private static void countingSortForRadix(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[10];
        Arrays.fill(count, 0);
        
        for (int num : array) {
            count[(num / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, array, 0, array.length);
    }

    // Bucket Sort
    private static int[] bucketSort(int[] array) { // Divide el rango en 'buckets' y distribuye los elementos
        int max = Arrays.stream(array).max().getAsInt();
        int min = Arrays.stream(array).min().getAsInt();
        int bucketCount = 10;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount); //Llena los buckets, ordena cada uno y combina
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        
        for (int num : array) {
            int index = (num - min) * (bucketCount - 1) / (max - min);
            buckets.get(index).add(num);
        }
        
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                array[index++] = num;
            }
        }
        return array;
    }
}