package aima.core.GeneticNN;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import aima.core.search.framework.Metrics;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import aima.core.util.Util;
import java.util.Collection;

public class GeneticNN<A> {

    protected static final String POPULATION_SIZE = "populationSize";
    protected static final String ITERATIONS = "iterations";
    protected static final String TIME_IN_MILLISECONDS = "timeInMilliseconds";

    protected Metrics metrics = new Metrics();

    protected int individualLength;
    protected List<A> finiteAlphabet;
    protected double mutationProbability;
    protected Random random;

    /* CODE START ≈ 1 líneas */
        // test commit
    /* CODE END */
    
    
    public GeneticNN(/* 4 atributos */) {
        /* CODE START ≈ 1 línea */
        ...
        /* CODE END */
    }

    public GeneticNN(/* 5 atributos */) {
    /* CODE START ≈ 5 líneas */
     ...
    /* CODE END */
    assert (this.mutationProbability >= 0.0 && this.mutationProbability <= 1.0);
    }

    public Individual<A> geneticAlgorithm(Set<Individual<A>> population, FitnessFunction<A> fitnessFn, GoalTest goalTest, long maxTimeMilliseconds) {
        Individual<A> bestIndividual = null;
        population = new LinkedHashSet<Individual<A>>(population);
        validatePopulation(population);
        clearInstrumentation();
        setPopulationSize(population.size());
        long startTime = System.currentTimeMillis();
        int cnt = 0;
        do {
            bestIndividual = nextGeneration(population, fitnessFn);
            cnt++;
            if (maxTimeMilliseconds > 0L) {
                if ((System.currentTimeMillis() - startTime) > maxTimeMilliseconds) {
                    break;
                }
            }
        } while (!goalTest.isGoalState(bestIndividual));
        setIterations(cnt);
        setTimeInMilliseconds(System.currentTimeMillis() - startTime);
        return bestIndividual;
    }

    public void clearInstrumentation() {
        setPopulationSize(0);
        setIterations(0);
        setTimeInMilliseconds(0L);
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public int getPopulationSize() {
        return metrics.getInt(POPULATION_SIZE);
    }

    public void setPopulationSize(int size) {
        metrics.set(POPULATION_SIZE, size);
    }

    public int getIterations() {
        return metrics.getInt(ITERATIONS);
    }

    public void setIterations(int cnt) {
        metrics.set(ITERATIONS, cnt);
    }

    public long getTimeInMilliseconds() {
        return metrics.getLong(TIME_IN_MILLISECONDS);
    }

    public void setTimeInMilliseconds(long time) {
        metrics.set(TIME_IN_MILLISECONDS, time);
    }

    protected Individual<A> nextGeneration(Set<Individual<A>> population, FitnessFunction<A> fitnessFn) {
        /* CODE START ≈ 40 líneas */
         ...
        /* CODE END */
    }

    protected Individual<A> randomSelection(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
        /* CODE START ≈ 19 líneas */
         ...
        /* CODE END */
    }

    protected Individual<A> reproduce(Individual<A> x, Individual<A> y) {
        /* CODE START ≈ 9 líneas */
        ...
       /* CODE END */
    }

    /* Muta un individuo pasado por parámetro y lo devuelve */
    protected Individual<A> mutate(Individual<A> child) {
        int mutateOffset = randomOffset(individualLength);
        int alphaOffset = randomOffset(finiteAlphabet.size());
        List<A> mutatedRepresentation = new ArrayList<A>(child.getRepresentation());
        mutatedRepresentation.set(mutateOffset, finiteAlphabet.get(alphaOffset));
        Individual<A> mutatedChild = new Individual<A>(mutatedRepresentation);
        /* Recarga el alfabeto */
        int size = finiteAlphabet.size();
        finiteAlphabet.clear();
        finiteAlphabet.addAll((Collection<? extends A>) fitnessFunction.getFiniteAlphabetForBoardOfSize(size));
        return mutatedChild;
    }

    /* Devuelve el mejor individuo de la población en base a la función de fitness */
    protected Individual<A> retrieveBestIndividual(Set<Individual<A>> population, FitnessFunction<A> fitnessFn) {
        Individual<A> bestIndividual = null;
        double bestSoFarFValue = Double.NEGATIVE_INFINITY;
        for (Individual<A> individual : population) {
            double fValue = fitnessFn.apply(individual);
            if (fValue > bestSoFarFValue) {
                bestIndividual = individual;
                bestSoFarFValue = fValue;
            }
        }
        return bestIndividual;
    }

    /* Genera un número aleatorio */
    protected int randomOffset(int length) {
        return random.nextInt(length);
    }

    /* Valida la población comprobando su tamaño */
    protected void validatePopulation(Set<Individual<A>> population) {
        if (population.size() < 1) {
            throw new IllegalArgumentException("Must start with at least a population of size 1");
        }
        for (Individual<A> individual : population) {
            if (individual.length() != this.individualLength) {
                throw new IllegalArgumentException("Individual [" + individual + "] in population is not the required length of " + this.individualLength);
            }
        }
    }

}
