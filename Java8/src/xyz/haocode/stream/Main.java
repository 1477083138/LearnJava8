package xyz.haocode.stream;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.*;

/**
 *
 * @author LiuHao
 * @date 2020/4/27 11:43
 * @description Stream流库操作
*/
public class Main {

    public static void main(String[] args) throws Exception{

        //获取单词列表中指定单词长度的数量
        String contents = new String(Files.readAllBytes(Paths.get("files/words.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\n"));

        int count = getWordsOfLengthCount(words,10);
        System.out.println(count);


        //流的创建
        //Stream.of(),创建任意数量引元的流
        Stream<String> stringStream = Stream.of("1","2","3");
        Stream<Integer> integerStream = Stream.of(1,2,3,4);
        stringStream.forEach(System.out::print);
        integerStream.forEach(System.out::print);
        System.out.println();

        //Arrays.Stream(array,from,to)
        System.out.println("========Arrays.Stream(array,from,to)=======");
        String[] stringArr = {"kkk","good","bay","zookeeper","xxx"};
        Stream<String> wordStream = Arrays.stream(stringArr,1,2);
        System.out.println(wordStream.count());
        //流执行应用终止操作，就不能再使用了，否则会报Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        //wordStream.forEach(System.out::println);

        //Stream.empty()
        System.out.println("========Stream.empty()=======");
        Stream<String> silent = Stream.empty();
        System.out.println(silent.count());

        //创建无限流的静态方法
        //Stream.generate(Supplier<T>)
        System.out.println("=========Stream.generate()======");
        Stream<Double> generateRandomStream = Stream.generate(Math::random);
        Stream<String> generateStringStream = Stream.generate(()->"1");
        //generateRandomStream.forEach(System.out::println);
       // generateStringStream.forEach(System.out::println);


        //Stream.Interate(T seed,UnaryOperation<T>) seed为种子，UnaryOperation不断操作在上一次的结果上。
        System.out.println("======Stream.Interate(T seed,UnaryOperation<T>)=======");
        Stream<BigInteger> bigIntegerStream = Stream.iterate(BigInteger.ZERO,n -> n.add(BigInteger.ONE));
        //bigIntegerStream.forEach(System.out::println);

        //Files.line(path,utf-8)
        System.out.println("======Files.line(path,utf-8)=======");
        Stream<String> words1 = Files.lines(Paths.get("files/words.txt"),StandardCharsets.UTF_8);
        words1.forEach(System.out::println);


        //filter,map,flatMap方法
        //使用Supplier.get()方法重用流
        Supplier<Stream<String>> streamSupplier = () -> Stream.of(stringArr);
        streamSupplier.get().map(String::toUpperCase).forEach(System.out::println);

        //flatMap
        Stream<String> flatMapStream = streamSupplier.get().flatMap(s->Stream.of(s));
        flatMapStream.forEach(System.out::println);

        //抽取子流和连接流
        Supplier<Stream<BigInteger>> supplier = ()->Stream.iterate(BigInteger.ZERO,n -> n.add(BigInteger.ONE));
        //Stream.limit(n) 产生一个流，其中包含了当前流中最初的n个元素
        System.out.println("======Stream.limit(n)=======");
        supplier.get().limit(10).forEach(System.out::println);

        //Stream.skip(n) 产生一个流，它的元素是当前流中除了前n个元素之外的所有元素
        System.out.println("======Stream.skip(n)=======");
        supplier.get().skip(10).limit(11).forEach(System.out::println);

        //Stream.concat(Stream,Stream) 产生一个流，它的元素是a的元素后面跟着b的元素
        System.out.println("======Stream.concat(Stream,Stream)=======");
        Stream<BigInteger> num_0_10_stream = supplier.get().limit(10);
        Stream<BigInteger> num_10_20_stream = supplier.get().skip(10).limit(10);
        Stream.concat(num_0_10_stream,num_10_20_stream).forEach(System.out::println);

        //其他流的转换
        //dinstct()
        System.out.println("======distinct()=======");
        String[] repeatedArr = {"xx","yy","zz","xx","xx","xxx","yyyy"};
        Stream.of(repeatedArr).distinct().forEach(System.out::println);

        //sorted(Comparator.comparing(lambda)) 产生一个流，它的元素是当前流中的所有元素按照顺序排列的。
        System.out.println("======sorted()=======");
        Stream.of(repeatedArr).sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
        //要求元素实现了Comparable的类的实例
        Stream.of(repeatedArr).sorted().forEach(System.out::println);
        //peek(lambda) 产生一个流，它与当前流中的元素相同，在获取其中每个元素时，会将其传递给action
        System.out.println("======peek()=======");
        Object[] powers = Stream.iterate(1.0,p -> p*2).peek(e-> debugFunction(e)).limit(10).toArray();

        /*简单约简*/
        //约简是一种终结操作，它们会将流约简为可以在程序中使用的非流值，而是一种Optional<T>类型的值。
        //操作：count
        System.out.println("======max(Comparator)=======");
        Optional<BigInteger> maxIntegerOptional = supplier.get().limit(10).max(BigInteger::compareTo);
        System.out.println("largest " + maxIntegerOptional.orElse(BigInteger.valueOf(-1L)));
        //操作：findFirst,findAny,max,min

        //断言类操作：anyMatch,allMatch,noneMatch,以上这些都是终结操作



        /*Optional<T>类型*/
        //是一种包装器对象，要么包装了类型T的对象，要么没有包装任何对象。
        System.out.println("===============Optional<T>=============");
        System.out.println("===============Optional<T>.orElse()=============");
        Stream<String> stringStream1 = Stream.empty();
        Optional<String> optionalString = stringStream1.findFirst();
        String result = optionalString.orElse("none");
        System.out.println(result);

        System.out.println("===============Optional<T>.orElseGet()=============");
        String result1 = optionalString.orElseGet(()-> Locale.getDefault().getDisplayName());
        System.out.println(result1);

        System.out.println("===============Optional<T>.orElseThrow()=============");
        //String result2 = optionalString.orElseThrow(IllegalStateException::new);
        //System.out.println(result2);

        //Optional<T>.ifPresent(v->process v);接受一个函数，如果该可选值存在，它会被传递给该函数，否则，不会发生任何事情，该函数不会返回任何值
        System.out.println("===============Optional<T>.ifPresent()=============");
        List<String> stringList = new ArrayList<>();
        //optionalString.ifPresent(v->stringList.add(v));
        optionalString.ifPresent(stringList::add);
        System.out.println(stringList);

        //Optional<T>.map() 如果想要处理函数的结果，应该使用map
        optionalString = Stream.of("1","2").findFirst();
        System.out.println("===============Optional<T>.map()=============");
        Optional<Boolean> added = optionalString.map(stringList::add);
        System.out.println(added);
        System.out.println(stringList);

        //不适合使用Optional值的方式

        /*创建Optional值*/

        //Optional<T>.of(T value) 产生一个具有给定值的Optinal，如果value为null，则会抛出异常。
        System.out.println("===============Optional<T>.of()=============");
        Optional<String> ofOptional= Optional.of("1");
        //Optional<T>.ofNullable(T value) 产生一个具有给定值的Optional，如果value为null，返回一个空的Optional
        Optional<String> ofNullableOptional = Optional.ofNullable(null);
        System.out.println(ofOptional);
        System.out.println("===============Optional<T>.ofNullable()=============");
        System.out.println(ofNullableOptional);
        //Optional<T>.empty() 产生一个空Optional
        System.out.println("===============Optional<T>.empty()=============");
        Optional emptyOptional = Optional.empty();
        System.out.println(emptyOptional);

        //用flatMap构建Optional值的函数
        //Optional<T>.flatMap() 产生将mapper应用于当前的Optional值所产生的结果忙活着在当前Optional为空时，产生一个空Optional
        System.out.println("===============Optional<T>.flatMap()=============");
        Optional<Double> optionalDouble = inverse(2.0).flatMap(Main::squareRoot);
        System.out.println(optionalDouble);

        //System.out.println(inverse(2.0).map(Main::squareRoot));

        /*收集结果*/

        //操作：forEach,forEachOrdered,toArray,iterator
        //toArray 会返回一个Object[]数组，如果想要让数组具有正确的类型，可以将其传递到数组构造器中：
        System.out.println("===============Stream<T>.toArray(T[])=============");
        BigInteger[] bigIntegers = supplier.get().limit(10).toArray(BigInteger[]::new);
        System.out.println(Arrays.toString(bigIntegers));

        //iterator 是一种终结操作
        System.out.println("===============Stream<T>.iterator()=============");
        Iterator<BigInteger> integerIterator = supplier.get().limit(10).iterator();
        while(integerIterator.hasNext()){
            System.out.print(integerIterator.next()+",");
        }
        System.out.println();

        //collect 接受一个Collector接口的实例。Collectors类提供大量用于生成公共收集器的工厂方法。
        System.out.println("===============Stream<T>.collect()=============");
        List<BigInteger> bigIntegerList = supplier.get().limit(10).collect(Collectors.toList());

        Set<BigInteger> bigIntegerSet = supplier.get().limit(10).collect(Collectors.toSet());

        //如果要控制获得集合的种类，可以使用Collectors.toCollection(T::new);

        TreeSet<BigInteger> bigIntegerTreeSet = supplier.get().limit(10).collect(Collectors.toCollection(TreeSet::new));

        //如果要通过连接操作来收集流中的所有字符串，可以调用Collectors.joining(),如果需要增加分割符，只需要将分割符作为joining的参数

        String joiningResult = supplier.get().limit(10).map(bigInteger -> bigInteger.toString()).collect(Collectors.joining());
        System.out.println(joiningResult);

        String joiningResult1 = supplier.get().limit(10).map(bigInteger -> bigInteger.toString()).collect(Collectors.joining("-"));

        System.out.println(joiningResult1);

        //Collectors.summarizing() 如果需要将流的结果约简为总和，平均值，最大值，最小值，可以使用summarizing(Int|Long|Double)

        IntSummaryStatistics summaryStatistics = Stream.of("1","xx","u").collect(Collectors.summarizingInt(String::length));

        //IntSummaryStatistics summaryStatistics = supplier.get().limit(10).collect(Collectors.summarizingInt(BigInteger::intValue));
        double averageNum = summaryStatistics.getAverage();
        double maxNum = summaryStatistics.getMax();
        double minNum = summaryStatistics.getMin();
        double sum = summaryStatistics.getSum();
        long count1 = summaryStatistics.getCount();

        System.out.println(averageNum+" "+maxNum+" "+minNum+" "+sum+" "+count1);


        /*收集到映射表中*/

        //Collectors.toMap()
        /*产生一个收集器，它会产生一个映射表或者并发映射表，keyMapperhe valueMapper函数会应用于每个收集到的元素上，
        * 从而在所产生的映射表中生成一个键/值项。默认情况下，当两个元素产生相同的键时，会抛出IllegalStateException异常
        * 可以提供一个mergeFunction来合并具有相同键的值。默认情况下，其结果是一个HashMap或ConcurrentHashMap。可以提供
        * 一个mapSupplier，它会产生所期望的映射表实例*/
        System.out.println("==============Collectors.toMap===============");
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        //获取每个国家所用到的默认语言
        Map<String,String> languageNames = locales.collect(
                Collectors.toMap(
                        Locale::getDisplayName,
                        l -> l.getDisplayLanguage(l),
                        (existtingValue, newValue) -> existtingValue
                )
        );
        System.out.println(languageNames);

        //获取每个国家所用到的所有语言
        Stream<Locale> locales1 = Stream.of(Locale.getAvailableLocales());
        Map<String,Set<String>> countryLanguageSets = locales1.collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Collections.singleton(l.getDisplayLanguage()),
                        (a,b)->{
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }
                )
        );
        System.out.println(countryLanguageSets);

        //获取每个国家所用到的所有语言
        Stream<Locale> locales2 = Stream.of(Locale.getAvailableLocales());
        Map<String,Set<String>> countryLanguageSets1 = locales2.collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Collections.singleton(l.getDisplayLanguage()),
                        (a,b)->{
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        },
                        TreeMap::new

                )
        );

        System.out.println(countryLanguageSets1);
        /*群组和分区*/

        //Collectors.groupingBy() 产生一个收集器，它会产生一个映射表或者并发映射表，其键是将classifier应用于所有收集到的元素上所产生的的结果，
        //而值是由具有相同键元素构成的一个个列表。
        System.out.println("=============Collectors.groupingBy()===========");
        Supplier<Stream<Locale>> localeSupplier = () -> Stream.of(Locale.getAvailableLocales());
        Map<String,List<Locale>> countryTolocales = localeSupplier.get().collect(
                Collectors.groupingBy(Locale::getCountry)
        );
        System.out.println(countryTolocales);
        List<Locale> chList = countryTolocales.get("CH");
        System.out.println(chList);

        //Collectors.partitioningBy() 当分类函数是断言函数（即返回Boolean值的函数）时，流的元素可以分区为两个列表。
        System.out.println("=============Collectors.partitioning()===========");
        Map<Boolean,List<Locale>> englishAndOtherLocales = localeSupplier.get().collect(
                Collectors.partitioningBy(l -> l.getLanguage().equals("en"))
        );
        System.out.println(englishAndOtherLocales);
        List<Locale> englishAndOtherList = englishAndOtherLocales.get(true);
        System.out.println(englishAndOtherList);

        /*下游收集器*/

        //Collectors.groupingBy()产生的是一个映射表，它的每个值都是一个列表，如果想要以某种方式来处理这些列表，就需要提供一个下游收集器

        System.out.println("========Collectors.groupingBy()=========");
        Map<String,Set<Locale>> countryToLocaleSet = localeSupplier.get().collect(
                Collectors.groupingBy(
                        Locale::getCountry,
                        Collectors.toSet()
                )
        );
        System.out.println(countryToLocaleSet);
        //Java提供了多种可以将群组元素约简为数字的收集器
        //counting，summing(Int|Long|Double)
        Map<String,Long> countryCount = localeSupplier.get().collect(
                Collectors.groupingBy(
                        Locale::getCountry,
                        Collectors.counting()
                )
        );
        System.out.println(countryCount);

        //maxBy,minBy 接受一个比较器，作用于下游元素，并产生下游元素中的最大值和最小值。
        System.out.println("=================Collectors.maxBy(Comparator.comparing)===========");
        BigInteger[] bigIntegers1 = {new BigInteger("1"),new BigInteger("10")};
        List<BigInteger> bigintegerList1 = Arrays.stream(bigIntegers1).collect(Collectors.toList());
        BigInteger[] bigIntegers2 = {new BigInteger("2"),new BigInteger("9")};
        List<BigInteger> bigIntegerList2 = Arrays.stream(bigIntegers2).collect(Collectors.toList());
        Stream<List<BigInteger>> bigIntegerListStream = Stream.of(bigintegerList1,bigIntegerList2,bigIntegerList);
        Map<Integer,Optional<List>> bigIntegerMap = bigIntegerListStream.collect(
                Collectors.groupingBy(
                        b->b.size(),
                        Collectors.maxBy(Comparator.comparing(l->l.size()))
                )
        );
        System.out.println(bigIntegerMap);

        //mapping 产生将函数应用到下游结果上的收集器，并将函数值传递给另一个收集器
        //产生一个收集器，，它会产生一个映射表，其键是将mapper应用到收集到的数据上而产生的，其值是使用downStream收集器收集到具有相同键的元素
        System.out.println("==============Collector.mapping()=======");
        Map<String,List<String>> country2Languages = localeSupplier.get().collect(
                Collectors.groupingBy(
                        Locale::getDisplayCountry,
                        Collectors.mapping(
                                Locale::getDisplayLanguage,
                                Collectors.toList()
                        )
                )
        );
        System.out.println(country2Languages);


        /*Stream.reduce()方法*/
        //是一种用于从流中计算某个值的通用机制,其组简单的形式是接受一个二元函数，并从前两个元素开始持续应用它
        //Optional<T> Stream.reduce(BinaryOperator<T>)
        Optional<BigInteger> bigIntegerSum = supplier.get().limit(10).reduce((x,y)->x.add(y));
        System.out.println(bigIntegerSum);

        //T Stream.reduce(T,BinaryOperator<T>)
        BigInteger bigIntegerSum1 = supplier.get().limit(10).reduce(new BigInteger("0"),(x,y)->x.add(y));
        System.out.println(bigIntegerSum1);

        /*基本类型流*/
        //IntStream,DoubleStream,LongStream 用来直接存储基本类型值
        //Stream.mapToInt() 可以直接将对象流转换为基础类型流
        System.out.println("==========Stream.mapToInt()============");
        IntStream is = supplier.get().limit(10).mapToInt(BigInteger::intValue);
        is.forEach(System.out::println);

        System.out.println("==========Stream.mapToDouble()");
        Supplier<DoubleStream> doubleStreamSupplier = () -> supplier.get().limit(10).mapToDouble(BigInteger::doubleValue);
        OptionalDouble dsMax = doubleStreamSupplier.get().max();
        OptionalDouble dsMin = doubleStreamSupplier.get().min();
//        //toArray() 转换为数组，为基础类型数组
//        double[] dsArr = ds.toArray();
//        //计算和 为基础类型数组
//        double dsSum = ds.sum();
//        OptionalDouble dsAverage = ds.average();
        System.out.println(dsMax+" "+dsMin);




        /*并行流*/
        //Fork-Join框架

        //使用parallelStream(),或者Stream.parallel()将顺序流转换为并行流，在终结方法执行时，流处于并行模式，所有中间流操作都将被并行化
        //将单词长度分组计数
        //确保传递给并行流操作的任何函数多可以安全的并发执行，需要远离易变状态
        System.out.println("============parallelStream()==========");
        Map<Integer,Long> shortWordCounts = words.parallelStream()
                .filter(s -> s.length()<10)
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.counting()
                        )
                );
        System.out.println(shortWordCounts);

        //Collectors.groupingByConcurrent() 合并映射表的代价很高昂，该方法使用了共享的并发映射表，映射表中的值
        //不会与流中的顺序相同
        System.out.println("===============Collectors.groupingByConcurrent()==================");
        Map<Integer,List<String>> currentMapByWordCount = words.parallelStream()
                .collect(
                        Collectors.groupingByConcurrent(String::length)
                );
        System.out.println(currentMapByWordCount.keySet());
        System.out.println(currentMapByWordCount.values());


        Map<Integer,Long> wordsCount = words.parallelStream()
                .collect(
                        Collectors.groupingByConcurrent(
                                String::length,
                                Collectors.counting()
                        )
                );
        System.out.println(wordsCount);

        //Stream.unOrder()
        System.out.println("==============Stream.unOrder()====================");
        long wordsCount1 = words.stream().unordered().count();
        System.out.println(wordsCount1);
        /*注意：不要修改在执行某项流操作后会将元素返回到流中的集合，流不会收集它们的数据，数据总是在单独的集合中。
        * 如果修改了这种集合，那么流操作的结果就是未定义的。*/
        System.out.println("============流中不建议的操作===========");
        List<String> stringList1 = new ArrayList<>();
        stringList1.addAll(words);
        Stream<String> words2 = stringList1.stream();

        int m = stringList1.size();
        stringList1.add("funny");
        long n = words2.distinct().count();

        System.out.println(m+" | "+n);
        /*让并行流正常工作需要满足大量的条件：
        * 数据应该在内存中
        * 流应该可以被高效的分成若干个子部分，由数组或平衡二叉树支撑的流都可以工作的很好，Stream.iterate返回的结果不行
        * 流操作的工作量应该具有较大的规模，否则白用
        * 流操作不行该被阻塞*/

        /*不要将所有的流都转换成并行流，只有在对已经位于内存中的数据执行大量计算操作时，才应该使用并行流*/
    }

    /**
     * 获取单词列表中指定单词长度的数量
     * @param words 单词列表
     * @param length 指定单词长度
     * @return
     */
    public static Integer getWordsOfLengthCount(List<String> words,final Integer length){
        //words.stream().filter(word->word.length()>length).forEach(System.out::println);
        //words.stream().filter(word->word.contains("x")).filter(word->word.length()>length).forEach(System.out::println);
        //并行流
        return (int)words.parallelStream().filter(word -> word.length() > length).count();
        //顺序流
        //return (int) words.stream().filter(word -> word.length() > length).count();
    }

    /**
     *
     * @param o
     */
    public static void debugFunction(Object o){
        System.out.println("debug");
        System.out.println(o.toString());
    }

    /**
     *
     * @param x
     * @return
     */
    public static Optional<Double> inverse(Double x){
        return x == 0 ? Optional.empty() : Optional.of(1/x);
    }

    /**
     * 计算指定数字的平方根
     * @param x
     * @return
     */
    public static Optional<Double> squareRoot(Double x){
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }


}
