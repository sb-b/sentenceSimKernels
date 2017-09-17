/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimilarityKernels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tree.TreeNode;
//import edu.stanford.nlp.trees.*; 
import trigram.TrigramUnit;
/**
 *
 * @author hakime_asus
 */
public  class DTrigramSimKernel {
   
    
    
    public static double computeDTrigramSimilarity(ArrayList<TrigramUnit> trigramList1, ArrayList<TrigramUnit> trigramList2)//TreeNode<ArrayList<String>> node1, TreeNode<ArrayList<String>> node2)
    {
        double sim=0;
      //  ArrayList<TrigramUnit> trigramList1 = new ArrayList<>();
        //ArrayList<TrigramUnit> trigramList2 = new ArrayList<>();

     //   trigramList1 = createTrigrams(node1, trigramList1);
       // trigramList2 = createTrigrams(node2, trigramList2);
      //  sim = 1;
        sim = computeTreeSimilarity(trigramList1, trigramList2);
       // System.out.println(sim);
       // sim = computeTrigramCosineSimilarity(trigramList1, trigramList2);
        //System.out.println(sim);
        return sim;
    }
    
    
    
   //su an one level children
    public static ArrayList<TrigramUnit> createTrigrams(TreeNode<ArrayList<String>> node1, ArrayList<TrigramUnit> trigramList)
    {
           //TrigramUnit trigram = new TrigramUnit(node1);
       
           if(node1.children.size() ==0)
               return trigramList;
           else
           {
               TreeNode<ArrayList<String>> centerNode = node1;
               
               if(centerNode.children.size() > 1)
               {
//                   ArrayList<ArrayList<TreeNode<ArrayList<String>>>> lists = new ArrayList<>();
//                   for( int j = 0; j < centerNode.children.size(); j++)
//                   {
//                       TreeNode<ArrayList<String>> tmpNode1 = centerNode.children.get(j);
//                       ArrayList<TreeNode<ArrayList<String>>> list1 = new  ArrayList<TreeNode<ArrayList<String>>>();
//                       list1 = createDescendantsList(tmpNode1,list1);
//                       lists.add(list1);
//                   }
                   for (int j = 0; j < centerNode.children.size(); j++) {
                       
                       TreeNode<ArrayList<String>> tmpNode1 = centerNode.children.get(j);
                      // ArrayList<TreeNode<ArrayList<String>>> list1 = new  ArrayList<TreeNode<ArrayList<String>>>();
                       // list1 = createDescendantsList(tmpNode1,list1);
        //               ArrayList<TreeNode<ArrayList<String>>> list1 = lists.get(j);
                       
                       for (int l = j+1; l < centerNode.children.size(); l++) {
                           
                            TreeNode<ArrayList<String>> tmpNode2 = centerNode.children.get(l);
                         //  ArrayList<TreeNode<ArrayList<String>>> list2 = new  ArrayList<TreeNode<ArrayList<String>>>();
                         //   list2=    createDescendantsList(tmpNode2,list2);
                           // ArrayList<TreeNode<ArrayList<String>>> list2 = lists.get(l);
                            // Trigram units will be created
                            
//                            for (int m = 0; m < list1.size(); m++) {
//                                 TreeNode<ArrayList<String>> tmp1= list1.get(m);
//                                 
//                                for (int n = 0; n < list2.size(); n++) {
//                                   
//                                   TreeNode<ArrayList<String>> tmp2= list2.get(n);
//                                   
                                  
                                   
                                   if(Integer.parseInt(tmpNode1.data.get(1)) < Integer.parseInt(tmpNode2.data.get(1)))
                                   {
                                     // tmp1; leftNode 
                                     // tmp2;   rightNode
                                    TrigramUnit trigram1 = new TrigramUnit(centerNode, tmpNode1, tmpNode2);
                                    trigramList.add(trigram1);
                                   }
                                   else
                                   {
                                      // leftNode=tmp2;
                                     //  rightNode= tmp1;
                                    TrigramUnit trigram1 = new TrigramUnit(centerNode, tmpNode2, tmpNode1);
                                    trigramList.add(trigram1);
                                   }
                                            
//                                 
//                                }
//                               
//                           }
                            
                            // End of trigram unit creation
                            
                       }
                       
                       createTrigrams(centerNode.children.get(j), trigramList);
                   }
               }
               
           }
         

        
    return trigramList;
} 
    //bu two level'de kullanılıyor
  public static ArrayList<TreeNode<ArrayList<String>>> createDescendantsList(TreeNode<ArrayList<String>> node1, ArrayList<TreeNode<ArrayList<String>>> descendList)
  {
      
      descendList.add(node1);
      
      for(int i = 0; i< node1.children.size(); i++)
      {
          descendList.add(node1.children.get(i));
      }

//      if(node1.children.size() == 0)
//      {
//           descendList.add(node1);
//      }
//      else if(node1.children.size() > 1)
//      {
//           descendList.add(node1);
//           
//          for (int j = 0; j < node1.children.size(); j++) {
//              createDescendantsList(node1.children.get(j),descendList);
//          }
//      }
//      else
//      {
//          descendList.add(node1);
//          createDescendantsList(node1.children.get(0), descendList);
//      }
//      
       return descendList;
  }
  
  
  public static double computeTreeSimilarity(ArrayList<TrigramUnit>  trigramList1, ArrayList<TrigramUnit>  trigramList2)
  {
      double max_sim=0;
      double temp_sim =0;
      double total_max_sim =0;
     // System.out.println(trigramList1.size() + " " + trigramList2.size());
      for (int j = 0; j < trigramList1.size(); j++) {
          
          TrigramUnit tgu1= trigramList1.get(j);
          
          for (int l = 0; l < trigramList2.size(); l++) {
              
              TrigramUnit tgu2= trigramList2.get(l);
              
              temp_sim =  trigramUnitSimilarity(tgu1, tgu2);
              
              if(temp_sim > max_sim)
                   max_sim = temp_sim;
                  
          }
          
          temp_sim =0;
          total_max_sim = total_max_sim + max_sim;
      }
      
      if(trigramList1.size() >0)
       return (total_max_sim/trigramList1.size());
      else
          return 0;
  }
  
//  public static double computeTrigramCosineSimilarity(ArrayList<TrigramUnit>  trigramList1, ArrayList<TrigramUnit>  trigramList2)
//  {
//  
//      double max_sim=0;
//      double temp_sim =0;
//      double total_max_sim =0;
//      
//      for (int j = 0; j < trigramList1.size(); j++) {
//          
//          TrigramUnit tgu1= trigramList1.get(j);
//          
//          for (int l = 0; l < trigramList2.size(); l++) {
//              
//              TrigramUnit tgu2= trigramList2.get(l);
//              
//              temp_sim =  trigramUnitSimilarity(tgu1, tgu2);
//              
//             total_max_sim = total_max_sim + temp_sim;
//                  
//          }
//          
//      }
//      
//      if(trigramList1.size() >0 && trigramList2.size()>0)
//       return (total_max_sim/(trigramList1.size()*trigramList2.size()));
//      else
//          return 0;
//  }
  
  public static double trigramUnitSimilarity(TrigramUnit tg1, TrigramUnit tg2)
  {
      double similarity=1;
      
       double center_sim = DTSimKernel.similarity(tg1.returnCenter(),tg2.returnCenter());//nodeSimilarity(tg1.returnCenter(),tg2.returnCenter(),false);
       double left_sim = DTSimKernel.similarity(tg1.returnLeft(),tg2.returnLeft());//nodeSimilarity(tg1.returnLeft(), tg2.returnLeft(),true);
       double right_sim = DTSimKernel.similarity(tg1.returnRight(),tg2.returnRight());//nodeSimilarity(tg1.returnRight(), tg2.returnRight(),true);
       
       //cosine sim baseline #1
       similarity = center_sim * left_sim * right_sim;
       
       //#2
      // similarity = (center_sim + left_sim + right_sim) / 5;
      
      return similarity;
  }
  
  
      private static double nodeSimilarity(TreeNode<ArrayList<String>> node1, TreeNode<ArrayList<String>> node2, boolean notCenter)
        {

            
            double sim = 0;
            
               
            //trigram cos sim baseline #1
//            if(node1.data.get(0).toString().equals(node2.data.get(0).toString()) && node1.data.get(2).toString().equals(node2.data.get(2).toString()))
//                sim= sim + 1; // alpha =1 for each
            
           //onemli typelara bakıyoruz sadece #2
            if(node1.data.get(0).toString().equals(node2.data.get(0).toString())) 
            {
                if(notCenter) //center node değilse
                {
                    if(node1.data.get(2).toString().equals(node2.data.get(2).toString()))//types
                    {
                        sim = sim + 1;
                         if(isImportantType(node1.data.get(2).toString())) //#1 için burayı kapat sadece
                             sim= sim + 1; // alpha =1 for each
                    }
                }
                else //center node ise
                {
                    sim = sim + 1;
                }
            }
        
            return sim;
        }
    
  
      private static boolean isImportantType(String type)
      {
          List<String> important_types = new ArrayList();
          Collections.addAll(important_types,"cop", "agent", "acomp", "dobj", "iobj", "nsubj", "nsubjpass", "xsubj");
          if(important_types.contains(type))
                  return true;
          else
              return false;
      }
   
   
   
}
