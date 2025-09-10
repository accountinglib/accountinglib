package org.accountinglib.ai;

import com.github.tjake.jlama.model.AbstractModel;
import com.github.tjake.jlama.model.ModelSupport;
import com.github.tjake.jlama.model.functions.Generator;
import com.github.tjake.jlama.safetensors.DType;
import com.github.tjake.jlama.safetensors.prompt.PromptContext;
import com.github.tjake.jlama.util.Downloader;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Test using JLama to run a local LLM model for accounting related questions.
 *
 * Must add VM options:
 * --enable-preview --add-modules jdk.incubator.vector
 */
public class AccountingAITest {

    public static void sample() throws IOException {
        //String model = "tjake/Llama-3.2-1B-Instruct-JQ4";
        String model = "tjake/Mistral-7B-Instruct-v0.3-JQ4";

        String workingDirectory = "./models";

        String prompt = "In Norway, which VAT code and which percentage should be used for a food product sale? Answer with only the VAT percentage.";

        // Downloads the model or just returns the local path if it's already downloaded
        File localModelPath = new Downloader(workingDirectory, model).huggingFaceModel();

        // Loads the quantized model and specified use of quantized memory
        AbstractModel m = ModelSupport.loadModel(localModelPath, DType.F32, DType.I8);

        PromptContext ctx;
        if (m.promptSupport().isPresent()) {
            ctx = m.promptSupport()
                    .get()
                    .builder()
                    .addSystemMessage("You are a accounting system based in norway which writes short correct responses.")
                    .addUserMessage(prompt)
                    .build();
        } else {
            ctx = PromptContext.of(prompt);
        }

        System.out.println("Prompt: " + ctx.getPrompt() + "\n");
        Generator.Response r = m.generate(UUID.randomUUID(), ctx, 0.0f, 256, (s, f) -> {});
        System.out.println(r.responseText);
    }


    public static void main(String[] args) {
        System.out.println("AccountingLib-AI test using JLama.");
        try {
            AccountingAITest.sample();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
