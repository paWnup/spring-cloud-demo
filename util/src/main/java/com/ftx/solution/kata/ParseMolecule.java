package com.ftx.solution.kata;

import java.util.HashMap;
import java.util.Map;

/**
 * For a given chemical formula represented by a string,
 * count the number of atoms of each element contained in the molecule and return an object (associative array in PHP, Dictionary<string, int> in C#, Map<String,Integer> in Java).
 * <p>
 * For example:
 * <p>
 * String water = "H2O";
 * parseMolecule.getAtoms(water); // return [H: 2, O: 1]
 * <p>
 * String magnesiumHydroxide = "Mg(OH)2";
 * parseMolecule.getAtoms(magnesiumHydroxide); // return ["Mg": 1, "O": 2, "H": 2]
 * <p>
 * String fremySalt = "K4[ON(SO3)2]2";
 * parseMolecule.getAtoms(fremySalt); // return ["K": 4, "O": 14, "N": 2, "S": 4]
 * <p>
 * parseMolecule.getAtoms("pie"); // throw an IllegalArgumentException
 * As you can see, some formulas have brackets in them.
 * The index outside the brackets tells you that you have to multiply count of each atom inside the bracket on this index.
 * For example, in Fe(NO3)2 you have one iron atom, two nitrogen atoms and six oxygen atoms.
 * <p>
 * Note that brackets may be round, square or curly and can also be nested.
 * Index after the braces is optional.
 * s
 *
 * @author puan
 * @date 2019-04-12 9:34
 **/
public class ParseMolecule {

    public static Map<String, Integer> getAtoms(String formula) {
        // Your code here! K4[ON(SO3)2]2
        Map<String, Integer> data = new HashMap<>();
        return new HashMap<>();
    }
}
