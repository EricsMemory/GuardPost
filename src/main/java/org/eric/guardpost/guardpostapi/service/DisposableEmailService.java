package org.eric.guardpost.guardpostapi.service;

import org.eric.guardpost.guardpostapi.util.EmailUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Service
public class DisposableEmailService {

    private final Set<String> disposableDomains;

    /** Constructor for disposable email service */
    public DisposableEmailService(){
        this.disposableDomains = getDomains();
    }

    /** Method returning a set of strings from the provided file with emails */
    public Set<String> getDomains(){
        String filePath = "src/main/resources/disposable_email_blocklist.conf";
        //HashSet of emails, doesn't need any special kind of sorting
        Set<String> emails = new HashSet<>();

        //Parsing file with BufferedReader + FileReader and a loop to add each line to the created set
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for (String line; (line = br.readLine()) != null; ) {
                emails.add(line);
            }
        } catch (IOException e){
            System.out.println("Error reading email list");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return emails;
    }

    /** Method to determine if the email in question has a domain in the list of disposables */
    public boolean isDisposable(String email){
        String domain = EmailUtils.extractDomain(email);
        if (domain == null) return false;

        for (String comparator : disposableDomains) {
            if (domain.equalsIgnoreCase(comparator.trim())) {
                return true;
            }
        }
        return false;
    }

}
